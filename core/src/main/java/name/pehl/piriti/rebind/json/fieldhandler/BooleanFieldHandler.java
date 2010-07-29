package name.pehl.piriti.rebind.json.fieldhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.fieldhandler.AbstractFieldHandler;
import name.pehl.piriti.rebind.fieldhandler.FieldContext;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * @author $Author$
 * @version $Date$ $Revision: 364
 *          $
 */
public class BooleanFieldHandler extends AbstractFieldHandler
{
    /**
     * Returns <code>true</code> if the field type is boolean or Boolean,
     * <code>false</code> otherwise.
     * 
     * @param writer
     * @param fieldContext
     * @return
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.fieldhandler.FieldHandler#isValid(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.fieldhandler.FieldContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        if (!TypeUtils.isBoolean(fieldContext.getFieldType()))
        {
            CodeGeneration.skipField(writer, fieldContext, "Type is neither boolean nor Boolean");
        }
        return true;
    }


    /**
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.fieldhandler.FieldHandler#writeConverterCode(name.pehl.piriti.rebind.IndentedWriter,
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
        String jsonBoolean = fieldContext.newVariableName("AsJsonBoolean");
        writer.write("JSONBoolean %s = %s.isBoolean();", jsonBoolean, jsonValue);
        writer.write("if (%s != null) {", jsonBoolean);
        writer.indent();
        writer.write("%s = %s.booleanValue();", fieldContext.getValueVariable(), jsonBoolean);
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }


    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.fieldhandler.FieldHandler#writeSerialization(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.fieldhandler.FieldContext)
     */
    @Override
    public void writeSerialization(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        // TODO Add support for getter
        CodeGeneration.appendJsonKey(writer, fieldContext);
        CodeGeneration.append(writer, fieldContext, String.format("model.%s", fieldContext.getFieldName()));
    }
}
