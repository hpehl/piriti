package name.pehl.piriti.rebind.json.propertyhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;

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
    /**
     * Returns <code>true</code> if the field type is short, Short, int,
     * Integer, long, Long, float, Float, double or Double, <code>false</code>
     * otherwise.
     * 
     * @param writer
     * @param fieldContext
     * @return
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#isValid(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        if (fieldContext.isPrimitive())
        {
            JPrimitiveType primitiveType = fieldContext.getPrimitiveType();
            return primitiveType.equals(JPrimitiveType.BYTE) || primitiveType.equals(JPrimitiveType.SHORT)
                    || primitiveType.equals(JPrimitiveType.INT) || primitiveType.equals(JPrimitiveType.LONG)
                    || primitiveType.equals(JPrimitiveType.FLOAT) || primitiveType.equals(JPrimitiveType.DOUBLE);
        }
        else if (fieldContext.isClassOrInterface())
        {
            JClassType type = fieldContext.getClassOrInterfaceType();
            return type.getQualifiedSourceName().equals(Byte.class.getName())
                    || type.getQualifiedSourceName().equals(Short.class.getName())
                    || type.getQualifiedSourceName().equals(Integer.class.getName())
                    || type.getQualifiedSourceName().equals(Long.class.getName())
                    || type.getQualifiedSourceName().equals(Float.class.getName())
                    || type.getQualifiedSourceName().equals(Double.class.getName());
        }
        return false;
    }


    /**
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public void readInput(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        // If there's a path then get the JSON value using this path,
        // otherwise it is expected that the JSON value is the inputVariable
        // itself (e.g. an array of strings has no path information for the
        // array elements)
        String jsonValue = fieldContext.newVariableName("AsJsonValue");
        if (fieldContext.getPath() != null)
        {
            writer.write("JSONValue %s = %s.get(\"%s\");", jsonValue, fieldContext.getInputVariable(),
                    fieldContext.getPath());
        }
        else
        {
            writer.write("JSONValue %s = %s;", jsonValue, fieldContext.getInputVariable());
        }
        writer.write("if (%s != null) {", jsonValue);
        writer.indent();
        writer.write("if (%s.isNull() == null) {", jsonValue);
        writer.indent();
        String jsonNumber = fieldContext.newVariableName("AsJsonNumber");
        writer.write("JSONNumber %s = %s.isNumber();", jsonNumber, jsonValue);
        writer.write("if (%s != null) {", jsonNumber);
        writer.indent();
        String doubleValue = fieldContext.getValueVariable() + "AsDouble";
        writer.write("Double %s = new Double(%s.doubleValue());", doubleValue, jsonNumber);
        if (TypeUtils.isByte(fieldContext.getFieldType()))
        {
            writer.write("%s = %s.byteValue();", fieldContext.getValueVariable(), doubleValue);
        }
        else if (TypeUtils.isShort(fieldContext.getFieldType()))
        {
            writer.write("%s = %s.shortValue();", fieldContext.getValueVariable(), doubleValue);
        }
        else if (TypeUtils.isInteger(fieldContext.getFieldType()))
        {
            writer.write("%s = %s.intValue();", fieldContext.getValueVariable(), doubleValue);
        }
        else if (TypeUtils.isLong(fieldContext.getFieldType()))
        {
            writer.write("%s = %s.longValue();", fieldContext.getValueVariable(), doubleValue);
        }
        else if (TypeUtils.isFloat(fieldContext.getFieldType()))
        {
            writer.write("%s = %s.floatValue();", fieldContext.getValueVariable(), doubleValue);
        }
        else if (TypeUtils.isDouble(fieldContext.getFieldType()))
        {
            writer.write("%s = %s;", fieldContext.getValueVariable(), doubleValue);
        }
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }


    @Override
    public void markupStart(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        CodeGeneration.appendJsonKey(writer, fieldContext);
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        if (fieldContext.getFieldType().isPrimitive() == null)
        {
            // if the Number object is null, append 0
            writer.write("if (%s == null) {", fieldContext.getValueVariable());
            writer.indent();
            writer.write("%s.append(\"0\");", fieldContext.getBuilderVariable());
            writer.outdent();
            writer.write("}");
            writer.write("else {");
            writer.indent();
            CodeGeneration.appendJsonValue(writer, fieldContext, false);
            writer.outdent();
            writer.write("}");
        }
        else
        {
            CodeGeneration.appendJsonValue(writer, fieldContext, false);
        }
    }


    /**
     * Empty!
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#markupEnd(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public void markupEnd(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
    }
}
