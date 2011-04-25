package name.pehl.piriti.rebind.json.propertyhandler;

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
public class ArrayPropertyHandler extends AbstractJsonPropertyHandler
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


    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param propertyContext
     * @param propertyHandlerLookup
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.xml.propertyhandler.ConverterPropertyHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
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

        // The nested property context is created *without* a path. The nested
        // property handler must take care of this!
        String valueVariableAsList = propertyContext.getVariableNames().newVariableName("AsList");
        PropertyContext nestedPropertyContext = propertyContext.createNested(elementType, COLLECTION_ELEMENT_PATH);
        PropertyHandler nestedHandler = propertyHandlerLookup.lookup(nestedPropertyContext);
        if (!nestedHandler.isValid(writer, nestedPropertyContext))
        {
            warn("No PropertyHandler found for element type %s in %s", elementType, propertyContext);
            return;
        }

        getOrSelectJson(writer, propertyContext);
        writer.write("if (%s != null) {", jsonValueVariable);
        writer.indent();
        writer.write("JSONArray jsonArray = %s.isArray();", jsonValueVariable);
        writer.write("if (jsonArray != null) {");
        writer.indent();
        writer.write("int size = jsonArray.size();");
        writer.write("List<%1$s> %2$s = new ArrayList<%1$s>();", elementType.getParameterizedQualifiedSourceName(),
                valueVariableAsList);
        writer.write("for (int i = 0; i < size; i++) {");
        writer.indent();
        writer.write("JSONValue %s = jsonArray.get(i);", nestedPropertyContext.getVariableNames().getInputVariable());
        writer.write("if (%1$s != null && %1$s.isNull() == null) {", nestedPropertyContext.getVariableNames()
                .getInputVariable());
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
        writer.outdent();
        writer.write("}");
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext,
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

        // The nested property context is created *without* a path. The nested
        // property handler must take care of this!
        PropertyContext nestedPropertyContext = propertyContext.createNested(elementType, COLLECTION_ELEMENT_PATH);
        PropertyHandler nestedHandler = propertyHandlerLookup.lookup(nestedPropertyContext);
        if (!nestedHandler.isValid(writer, nestedPropertyContext))
        {
            warn("No PropertyHandler found for element type %s in %s", elementType, propertyContext);
            return;
        }

        writer.write("if (%s == null) {", propertyContext.getVariableNames().getValueVariable());
        writer.indent();
        writer.write("%s.append(\"null\");", propertyContext.getVariableNames().getBuilderVariable());
        writer.outdent();
        writer.write("}");
        writer.write("else {");
        writer.indent();
        // Iterate over values
        writer.write("%s.append(\"[\");", propertyContext.getVariableNames().getBuilderVariable());
        writer.write("for (int i = 0; i < %s.length; i++ ) {", propertyContext.getVariableNames().getValueVariable());
        writer.indent();

        nestedHandler.log(writer, nestedPropertyContext);
        nestedHandler.declare(writer, nestedPropertyContext);
        // Replace nestedHandler.readField(writer, nestedFieldContext) with
        writer.write("%s = %s[i];", nestedPropertyContext.getVariableNames().getValueVariable(), propertyContext
                .getVariableNames().getValueVariable());
        // No nestedHandler.markupStart(writer, nestedFieldContext); since we're
        // in an JSON array
        nestedHandler.writeValue(writer, nestedPropertyContext, propertyHandlerLookup);
        // No nestedHandler.markupEnd(writer, nestedFieldContext); since we're
        // in an JSON array

        writer.write("if (i < %s.length - 1) {", propertyContext.getVariableNames().getValueVariable());
        writer.indent();
        writer.write("%s.append(\",\");", propertyContext.getVariableNames().getBuilderVariable());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("%s.append(\"]\");", propertyContext.getVariableNames().getBuilderVariable());
        writer.outdent();
        writer.write("}");
    }
}
