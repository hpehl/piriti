package name.pehl.piriti.rebind.xml.propertyhandler;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.propertyhandler.AbstractArrayPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.MappingType;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyStyle;
import name.pehl.piriti.rebind.propertyhandler.VariableNames;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JArrayType;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;

/**
 * {@link PropertyHandler} for arrays.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class ArrayPropertyHandler extends AbstractArrayPropertyHandler
{
    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param propertyContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.xml.propertyhandler.ConverterPropertyHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public void readInput(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        JArrayType arrayType = propertyContext.getArrayType();
        JType componentType = arrayType.getComponentType();
        JPrimitiveType primitiveComponentType = componentType.isPrimitive();
        if (primitiveComponentType != null)
        {
            try
            {
                componentType = propertyContext.getTypeOracle().getType(
                        primitiveComponentType.getQualifiedBoxedSourceName());
            }
            catch (NotFoundException e)
            {
                throw new UnableToCompleteException();
            }
        }
        String valueVariableAsList = propertyContext.getVariableNames().newVariableName("AsList");
        String nestedElementVariable = propertyContext.getVariableNames().newVariableName("NestedElement");
        String nestedElementsVariable = propertyContext.getVariableNames().newVariableName("NestedElements");
        String nestedValueVariable = propertyContext.getVariableNames().newVariableName("NestedValue");
        String nestedXpath = ".";
        if (componentType.isPrimitive() != null || TypeUtils.isBasicType(componentType)
                || componentType.isEnum() != null)
        {
            nestedXpath += "/text()";
        }

        // TODO Implement usage of setters
        VariableNames variableNames = new VariableNames(nestedElementVariable, nestedValueVariable, propertyContext
                .getVariableNames().getBuilderVariable());
        PropertyContext nestedFieldContext = new PropertyContext(propertyContext.getTypeOracle(),
                propertyContext.getHandlerRegistry(), propertyContext.getReaderOrWriter(), propertyContext.getClazz(),
                componentType, propertyContext.getName(), nestedXpath, propertyContext.getFormat(),
                propertyContext.isStripWsnl(), propertyContext.getConverter(), MappingType.MAPPING,
                PropertyStyle.FIELD, propertyContext.getGetter(), propertyContext.getSetter(), variableNames);
        PropertyHandler nestedHandler = propertyContext.getHandlerRegistry().findPropertyHandler(nestedFieldContext);
        if (!nestedHandler.isValid(writer, nestedFieldContext))
        {
            return;
        }
        writer.write("List<Element> %s = filterElements(%s.selectNodes(\"%s\"));", nestedElementsVariable,
                propertyContext.getVariableNames().getInputVariable(), propertyContext.getPath());
        writer.write("if (!%1$s.isEmpty()) {", nestedElementsVariable);
        writer.indent();
        writer.write("List<%1$s> %2$s = new ArrayList<%1$s>();", componentType.getParameterizedQualifiedSourceName(),
                valueVariableAsList);
        writer.write("for (Element %s : %s) {", nestedElementVariable, nestedElementsVariable);
        writer.indent();
        nestedHandler.comment(writer, nestedFieldContext);
        nestedHandler.declare(writer, nestedFieldContext);
        nestedHandler.readInput(writer, nestedFieldContext);
        writer.write("if (%s != null) {", nestedFieldContext.getVariableNames().getValueVariable());
        writer.indent();
        writer.write("%s.add(%s);", valueVariableAsList, nestedFieldContext.getVariableNames().getValueVariable());
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
                    componentType.getQualifiedSourceName(), valueVariableAsList);
        }
        writer.write("for(%s currentValue : %s) {", componentType.getQualifiedSourceName(), valueVariableAsList);
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
    public void markupStart(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("// markupStart() NYI");
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("// writeValue() NYI");
    }


    @Override
    public void markupEnd(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("// markupEnd() NYI");
    }
}
