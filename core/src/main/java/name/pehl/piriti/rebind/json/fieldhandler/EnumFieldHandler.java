package name.pehl.piriti.rebind.json.fieldhandler;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.AbstractEnumFieldHandler;
import name.pehl.piriti.rebind.fieldhandler.FieldContext;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * {@link FieldHandler} for enum types. This implementation reads the JSON data
 * as string and tries to convert it using <code>enumType.valueOf(String)</code>
 * .
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class EnumFieldHandler extends AbstractEnumFieldHandler
{
    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.xml.fieldhandler.ConverterFieldHandler#writeConverterCode(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.fieldhandler.FieldContext)
     */
    @Override
    public void writeConverterCode(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        // If there's a path then get the JSON value using this path,
        // otherwise it is expected that the JSON value is the inputVariable
        // itself (e.g. an array of strings has no path information for the
        // array elements)
        String jsonValue = fieldContext.newVariableName("AsJsonValue");
        if (fieldContext.getPath() != null)
        {
            writer.write("JSONValue %s = %s.get(\"%s\");", jsonValue, fieldContext.getInputVariable(), fieldContext
                    .getPath());
        }
        else
        {
            writer.write("JSONValue %s = %s;", jsonValue, fieldContext.getInputVariable());
        }
        writer.write("if (%s != null) {", jsonValue);
        writer.indent();
        writer.write("if (%s.isNull() == null) {", jsonValue);
        writer.indent();
        String jsonString = fieldContext.newVariableName("AsJsonString");
        writer.write("JSONString %s = %s.isString();", jsonString, jsonValue);
        writer.write("if (%s != null) {", jsonString);
        writer.indent();
        writer.write("try {");
        writer.indent();
        writer.write("%s = %s.valueOf(%s.stringValue());", fieldContext.getValueVariable(), fieldContext.getEnumType()
                .getQualifiedSourceName(), jsonString);
        writer.outdent();
        writer.write("}");
        writer.write("catch (IllegalArgumentException e1) {");
        writer.indent();
        writer.write("try {");
        writer.indent();
        writer.write("%s = %s.valueOf(%s.stringValue().toUpperCase());", fieldContext.getValueVariable(), fieldContext.getEnumType()
                .getQualifiedSourceName(), jsonString);
        writer.outdent();
        writer.write("}");
        writer.write("catch (IllegalArgumentException e2) {}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }
}
