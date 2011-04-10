package name.pehl.piriti.rebind.json.propertyhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.json.JsonPathUtils;
import name.pehl.piriti.rebind.propertyhandler.AbstractEnumPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;

import com.google.gwt.core.ext.TreeLogger;
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
    public EnumPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    /**
     * Returns <code>false</code> if this property context is used with a writer
     * and a JSONPath expression is used,
     * 
     * @param writer
     * @param propertyContext
     * @return
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractArrayPropertyHandler#isValid(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        boolean valid = super.isValid(writer, propertyContext);
        if (valid && propertyContext.getTypeContext().isWriter() && JsonPathUtils.isJsonPath(propertyContext.getPath()))
        {
            CodeGeneration.skipProperty(writer, propertyContext,
                    "JSONPath expressions are not supported by this JsonWriter");
            return false;
        }
        return valid;
    }


    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param propertyContext
     * @param propertyHandlerRegistry
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.xml.propertyhandler.ConverterPropertyHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
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
        String jsonString = propertyContext.getVariableNames().newVariableName("AsJsonString");
        writer.write("JSONString %s = %s.isString();", jsonString, jsonValue);
        writer.write("if (%s != null) {", jsonString);
        writer.indent();

        if (propertyContext.useCustomConverter())
        {
            writer.write("String %s = %s.stringValue();",
                    propertyContext.getVariableNames().getValueAsStringVariable(), jsonString);
            CodeGeneration.useConverterForReading(writer, propertyContext);
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
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerRegistry propertyHandlerRegistry) throws UnableToCompleteException
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
