package name.pehl.piriti.rebind.xml.propertyhandler;

import java.util.logging.Level;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerLookup;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;

/**
 * {@link PropertyHandler} for arrays.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class ArrayPropertyHandler extends AbstractXmlPropertyHandler
{
    public ArrayPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        JType elementType = getElementType(propertyContext);
        if (elementType.isArray() != null)
        {
            skipProperty(writer, propertyContext, "Multi-dimensional arrays are not supported");
            return false;
        }
        if (TypeUtils.isCollection(elementType) || TypeUtils.isMap(elementType))
        {
            skipProperty(writer, propertyContext, "Arrays of collections / maps are not supported");
            return false;
        }
        return true;
    }


    protected JType getElementType(PropertyContext propertyContext) throws UnableToCompleteException
    {
        return propertyContext.getArrayType().getComponentType();
    }


    @Override
    public void readInput(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerLookup propertyHandlerLookup) throws UnableToCompleteException
    {
        JType elementType = getElementType(propertyContext);
        JPrimitiveType primitiveComponentType = elementType.isPrimitive();
        if (primitiveComponentType != null)
        {
            try
            {
                elementType = propertyContext.getTypeContext().getTypeOracle()
                        .getType(primitiveComponentType.getQualifiedBoxedSourceName());
            }
            catch (NotFoundException e)
            {
                die("No type found for %s", primitiveComponentType.getQualifiedBoxedSourceName());
            }
        }

        String nestedXpath = ".";
        String valueVariableAsList = propertyContext.getVariableNames().newVariableName("AsList");
        String nestedElementsVariable = propertyContext.getVariableNames().newVariableName("NestedElements");
        PropertyContext nestedPropertyContext = propertyContext.createNested(elementType, nestedXpath);
        PropertyHandler nestedHandler = propertyHandlerLookup.lookup(nestedPropertyContext);
        if (!nestedHandler.isValid(writer, nestedPropertyContext))
        {
            return;
        }

        writer.write("List<Element> %s = filterElements(%s.selectNodes(\"%s\"));", nestedElementsVariable,
                propertyContext.getVariableNames().getInputVariable(), propertyContext.getPathOrName());
        writer.write("if (!%1$s.isEmpty()) {", nestedElementsVariable);
        writer.indent();
        writer.write("List<%1$s> %2$s = new ArrayList<%1$s>();", elementType.getParameterizedQualifiedSourceName(),
                valueVariableAsList);
        writer.write("for (Element %s : %s) {", nestedPropertyContext.getVariableNames().getInputVariable(),
                nestedElementsVariable);
        writer.indent();
        nestedHandler.log(writer, nestedPropertyContext);
        nestedHandler.declare(writer, nestedPropertyContext);
        nestedHandler.readInput(writer, nestedPropertyContext, propertyHandlerLookup);
        writer.write("if (%s != null) {", nestedPropertyContext.getVariableNames().getValueVariable());
        writer.indent();
        writer.write("%s.add(%s);", valueVariableAsList, nestedPropertyContext.getVariableNames().getValueVariable());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("if (!%s.isEmpty()) {", valueVariableAsList);
        writer.indent();
        writer.write("int index = 0;");
        if (primitiveComponentType != null)
        {
            writer.write("%s = new %s[%s.size()];", propertyContext.getVariableNames().getValueVariable(),
                    primitiveComponentType.getQualifiedSourceName(), valueVariableAsList);
        }
        else
        {
            writer.write("%s = new %s[%s.size()];", propertyContext.getVariableNames().getValueVariable(),
                    elementType.getQualifiedSourceName(), valueVariableAsList);
        }
        writer.write("for(%s currentValue : %s) {", elementType.getQualifiedSourceName(), valueVariableAsList);
        writer.indent();
        writer.write("%s[index] = currentValue;", propertyContext.getVariableNames().getValueVariable());
        writer.write("index++;");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerLookup propertyHandlerLookup) throws UnableToCompleteException
    {
        CodeGeneration.log(writer, Level.WARNING, "writeValue() NYI");
    }
}
