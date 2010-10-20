package name.pehl.piriti.rebind.json.propertyhandler;

import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.json.JsonWriter;
import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.propertyhandler.AbstractRegistryPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * {@link PropertyHandler} implementation for types with an own {@link JsonReader}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class JsonRegistryPropertyHandler extends AbstractRegistryPropertyHandler
{
    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.xml.propertyhandler.ConverterFieldHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public void readInput(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        String readerVariable = startReader(writer, fieldContext, "jsonRegistry",
                fieldContext.getClassOrInterfaceType());

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
        writer.write("%s = %s.read(%s.toString());", fieldContext.getValueVariable(), readerVariable, jsonValue);
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");

        endReader(writer);
    }


    @Override
    public void markupStart(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        CodeGeneration.appendJsonKey(writer, fieldContext);
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        writer.write("if (%s == null) {", fieldContext.getValueVariable());
        writer.indent();
        writer.write("%s.append(\"null\");", fieldContext.getBuilderVariable());
        writer.outdent();
        writer.write("}");
        writer.write("else {");
        writer.indent();
        String writerVariable = startWriter(writer, fieldContext, "jsonRegistry",
                fieldContext.getClassOrInterfaceType());
        writer.write("%s.append(%s.toJson(%s));", fieldContext.getBuilderVariable(), writerVariable,
                fieldContext.getValueVariable());
        endReader(writer);
        writer.outdent();
        writer.write("}");
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


    @Override
    protected String getReaderClassname()
    {
        return JsonReader.class.getName();
    }


    @Override
    protected String getWriterClassname()
    {
        return JsonWriter.class.getName();
    }
}
