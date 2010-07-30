package name.pehl.piriti.rebind.json.fieldhandler;

import name.pehl.piriti.client.converter.Converter;
import name.pehl.piriti.client.converter.ConverterRegistry;
import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.AbstractConverterFieldHandler;
import name.pehl.piriti.rebind.fieldhandler.FieldContext;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.json.client.JSONString;

/**
 * {@link FieldHandler} implementation which uses a {@link Converter} from the
 * {@link ConverterRegistry}.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class ConverterFieldHandler extends AbstractConverterFieldHandler
{
    /**
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.fieldhandler.AbstractConverterFieldHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.fieldhandler.FieldContext)
     */
    @Override
    public void readInput(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        super.readInput(writer, fieldContext);
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }


    /**
     * Reads the string value using {@link JSONString#stringValue()}
     * 
     * @param writer
     * @param fieldContext
     * @see name.pehl.piriti.rebind.fieldhandler.AbstractConverterFieldHandler#readInputAsString(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.fieldhandler.FieldContext)
     */
    @Override
    protected void readInputAsString(IndentedWriter writer, FieldContext fieldContext)
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
        String jsonString = fieldContext.getValueVariable() + "AsJsonString";
        writer.write("JSONString %s = %s.isString();", jsonString, jsonValue);
        writer.write("if (%s != null) {", jsonString);
        writer.indent();
        writer.write("String %s = %s.stringValue();", fieldContext.getValueAsStringVariable(), jsonString);
    }


    @Override
    public void markupStart(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        CodeGeneration.appendJsonKey(writer, fieldContext);
    }


    @Override
    public void writeValue(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("if (%s == null) {", fieldContext.getValueAsStringVariable());
        writer.indent();
        writer.write("%s.append(\"null\");", fieldContext.getBuilderVariable());
        writer.outdent();
        writer.write("}");
        writer.write("else {");
        writer.indent();
        writer.write("%s.append(\"\\\"\");", fieldContext.getBuilderVariable());
        writer.write("%s.append(%s);", fieldContext.getBuilderVariable(), fieldContext.getValueAsStringVariable());
        writer.write("%s.append(\"\\\"\");", fieldContext.getBuilderVariable());
        writer.outdent();
        writer.write("}");
    }


    /**
     * Empty!
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.fieldhandler.FieldHandler#markupEnd(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.fieldhandler.FieldContext)
     */
    @Override
    public void markupEnd(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
    }
}
