package name.pehl.piriti.rebind.json.propertyhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.json.JsonUtils;
import name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;

/**
 * @author $Author$
 * @version $Date$ $Revision: 364
 *          $
 */
public class NumberPropertyHandler extends AbstractPropertyHandler
{
    public NumberPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    /**
     * Returns <code>true</code> if the field type is short, Short, int,
     * Integer, long, Long, float, Float, double or Double, <code>false</code>
     * otherwise.
     * 
     * @param writer
     * @param propertyContext
     * @return
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#isValid(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        if (propertyContext.isPrimitive())
        {
            JPrimitiveType primitiveType = propertyContext.getPrimitiveType();
            return primitiveType.equals(JPrimitiveType.BYTE) || primitiveType.equals(JPrimitiveType.SHORT)
                    || primitiveType.equals(JPrimitiveType.INT) || primitiveType.equals(JPrimitiveType.LONG)
                    || primitiveType.equals(JPrimitiveType.FLOAT) || primitiveType.equals(JPrimitiveType.DOUBLE);
        }
        else if (propertyContext.isClassOrInterface())
        {
            JClassType type = propertyContext.getClassOrInterfaceType();
            return type.getQualifiedSourceName().equals(Byte.class.getName())
                    || type.getQualifiedSourceName().equals(Short.class.getName())
                    || type.getQualifiedSourceName().equals(Integer.class.getName())
                    || type.getQualifiedSourceName().equals(Long.class.getName())
                    || type.getQualifiedSourceName().equals(Float.class.getName())
                    || type.getQualifiedSourceName().equals(Double.class.getName());
        }
        if (propertyContext.getTypeContext().isWriter() && JsonUtils.isJsonPath(propertyContext.getPath()))
        {
            CodeGeneration.skipProperty(writer, propertyContext,
                    "JSONPath expressions are not supported by this JsonWriter");
            return false;
        }
        return false;
    }


    /**
     * @param writer
     * @param propertyContext
     * @param propertyHandlerRegistry
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public void readInput(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerRegistry propertyHandlerRegistry) throws UnableToCompleteException
    {
        String jsonValue = CodeGeneration.getOrSelectJson(writer, propertyContext);
        writer.write("if (%s != null) {", jsonValue);
        writer.indent();
        writer.write("if (%s.isNull() == null) {", jsonValue);
        writer.indent();
        String jsonNumber = propertyContext.getVariableNames().newVariableName("AsJsonNumber");
        writer.write("JSONNumber %s = %s.isNumber();", jsonNumber, jsonValue);
        writer.write("if (%s != null) {", jsonNumber);
        writer.indent();
        String doubleValue = propertyContext.getVariableNames().getValueVariable() + "AsDouble";
        writer.write("Double %s = new Double(%s.doubleValue());", doubleValue, jsonNumber);
        if (TypeUtils.isByte(propertyContext.getType()))
        {
            writer.write("%s = %s.byteValue();", propertyContext.getVariableNames().getValueVariable(), doubleValue);
        }
        else if (TypeUtils.isShort(propertyContext.getType()))
        {
            writer.write("%s = %s.shortValue();", propertyContext.getVariableNames().getValueVariable(), doubleValue);
        }
        else if (TypeUtils.isInteger(propertyContext.getType()))
        {
            writer.write("%s = %s.intValue();", propertyContext.getVariableNames().getValueVariable(), doubleValue);
        }
        else if (TypeUtils.isLong(propertyContext.getType()))
        {
            writer.write("%s = %s.longValue();", propertyContext.getVariableNames().getValueVariable(), doubleValue);
        }
        else if (TypeUtils.isFloat(propertyContext.getType()))
        {
            writer.write("%s = %s.floatValue();", propertyContext.getVariableNames().getValueVariable(), doubleValue);
        }
        else if (TypeUtils.isDouble(propertyContext.getType()))
        {
            writer.write("%s = %s;", propertyContext.getVariableNames().getValueVariable(), doubleValue);
        }
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }


    /**
     * @param writer
     * @param propertyContext
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler#readInputAsString(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.PropertyContext)
     */
    @Override
    protected void readInputAsString(IndentedWriter writer, PropertyContext propertyContext)
    {
        // TODO Implement me!
    }


    @Override
    public void markupStart(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        CodeGeneration.appendJsonKey(writer, propertyContext);
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerRegistry propertyHandlerRegistry) throws UnableToCompleteException
    {
        if (propertyContext.getType().isPrimitive() == null)
        {
            // if the Number object is null, append 0
            writer.write("if (%s == null) {", propertyContext.getVariableNames().getValueVariable());
            writer.indent();
            writer.write("%s.append(\"0\");", propertyContext.getVariableNames().getBuilderVariable());
            writer.outdent();
            writer.write("}");
            writer.write("else {");
            writer.indent();
            CodeGeneration.appendJsonValue(writer, propertyContext, false);
            writer.outdent();
            writer.write("}");
        }
        else
        {
            CodeGeneration.appendJsonValue(writer, propertyContext, false);
        }
    }


    /**
     * Empty!
     * 
     * @param writer
     * @param propertyContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#markupEnd(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public void markupEnd(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
    }
}
