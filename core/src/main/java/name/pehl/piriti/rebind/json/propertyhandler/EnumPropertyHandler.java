package name.pehl.piriti.rebind.json.propertyhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.propertyhandler.AbstractEnumPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * {@link PropertyHandler} for enum types. This implementation reads the JSON
 * data as string and tries to convert it using
 * <code>enumType.valueOf(String)</code> .
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class EnumPropertyHandler extends AbstractEnumPropertyHandler
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
        // If there's a path then get the JSON value using this path,
        // otherwise it is expected that the JSON value is the inputVariable
        // itself (e.g. an array of strings has no path information for the
        // array elements)
        String jsonValue = propertyContext.getVariableNames().newVariableName("AsJsonValue");
        if (propertyContext.getPath() != null)
        {
            writer.write("JSONValue %s = %s.get(\"%s\");", jsonValue, propertyContext.getVariableNames()
                    .getInputVariable(), propertyContext.getPath());
        }
        else
        {
            writer.write("JSONValue %s = %s;", jsonValue, propertyContext.getVariableNames().getInputVariable());
        }
        writer.write("if (%s != null) {", jsonValue);
        writer.indent();
        writer.write("if (%s.isNull() == null) {", jsonValue);
        writer.indent();
        String jsonString = propertyContext.getVariableNames().newVariableName("AsJsonString");
        writer.write("JSONString %s = %s.isString();", jsonString, jsonValue);
        writer.write("if (%s != null) {", jsonString);
        writer.indent();

        if (propertyContext.isCustomConverter())
        {
            String converterVariable = propertyContext.getVariableNames().newVariableName("ReadConverter");
            writer.write("Converter<%1$s> %2$s = GWT.create(%3$s.class);", propertyContext.getType()
                    .getQualifiedSourceName(), converterVariable, propertyContext.getConverter().getName());
            writer.write("%s = %s.convert(%s.stringValue(), null);", propertyContext.getVariableNames().getValueVariable(),
                    converterVariable, jsonString);
        }
        else
        {
            writer.write("try {");
            writer.indent();
            writer.write("%s = %s.valueOf(%s.stringValue());", propertyContext.getVariableNames().getValueVariable(),
                    propertyContext.getEnumType().getQualifiedSourceName(), jsonString);
            writer.outdent();
            writer.write("}");
            writer.write("catch (IllegalArgumentException e1) {");
            writer.indent();
            writer.write("try {");
            writer.indent();
            writer.write("%s = %s.valueOf(%s.stringValue().toUpperCase());", propertyContext.getVariableNames()
                    .getValueVariable(), propertyContext.getEnumType().getQualifiedSourceName(), jsonString);
            writer.outdent();
            writer.write("}");
            writer.write("catch (IllegalArgumentException e2) {}");
            writer.outdent();
            writer.write("}");
        }
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
        CodeGeneration.appendJsonKey(writer, propertyContext);
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("if (%s == null) {", propertyContext.getVariableNames().getValueVariable());
        writer.indent();
        writer.write("%s.append(\"null\");", propertyContext.getVariableNames().getBuilderVariable());
        writer.outdent();
        writer.write("}");
        writer.write("else {");
        writer.indent();
        CodeGeneration.appendJsonValue(writer, propertyContext, true);
        writer.outdent();
        writer.write("}");
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
