package name.pehl.piriti.rebind.json.fieldhandler;

import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.AbstractRegistryFieldHandler;
import name.pehl.piriti.rebind.fieldhandler.FieldContext;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * {@link FieldHandler} implementation for types with an own {@link JsonReader}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class JsonRegistryFieldHandler extends AbstractRegistryFieldHandler
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
        // Cast because subclasses might use a subtype of getReaderClassname()
        JClassType classType = fieldContext.getClassOrInterfaceType();
        String readerVariable = fieldContext.newVariableName("Reader");
        writer.write("%1$s<%2$s> %3$s = (%1$s)this.jsonRegistry.get(%2$s.class);", getReaderClassname(),
                classType.getQualifiedSourceName(), readerVariable);
        writer.write("if (%s != null) {", readerVariable);
        writer.indent();

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
        writer.outdent();
        writer.write("}");
    }


    @Override
    protected String getReaderClassname()
    {
        return JsonReader.class.getName();
    }
}
