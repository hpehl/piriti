package name.pehl.piriti.rebind.json.propertyhandler;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.VariableNames;
import name.pehl.piriti.rebind.json.JsonUtils;
import name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;

import org.apache.commons.lang.StringUtils;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Abstract base class for {@linkplain PropertyHandler}s used for JSON
 * (de)serialization.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public abstract class SingleValuePropertyHandler extends AbstractPropertyHandler
{
    // -------------------------------------------------------------- constants

    /**
     * JSONPath special characters.
     */
    private static final char[] JSON_PATH_SYMBOLS = new char[] {'$', '@', '.', '[', ']', '*', '#', ',', ':', '?', '(',
            ')',};


    // ----------------------------------------------------------- constructors

    public SingleValuePropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    // ---------------------------------------------------- overwritten methods

    /**
     * Returns <code>true</code> for {@linkplain JsonReader}s. For
     * {@linkplain JsonWriter}s this method returns <code>true</code> if no
     * JSONPath is used and <code>false</code> otherwise.
     * 
     * @param writer
     * @param propertyContext
     * @return <code>true</code> for {@linkplain JsonReader}s. For
     *         {@linkplain JsonWriter}s this method returns <code>true</code> if
     *         no JSONPath is used and <code>false</code> otherwise.
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#isValid(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.PropertyContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        if (propertyContext.getTypeContext().isWriter())
        {
            if (isJsonPath(propertyContext.getPath()))
            {
                skipProperty(writer, propertyContext, "The path \"" + propertyContext.getPath()
                        + "\" is a JSONPath expressions which is not supported by this JsonWriter");
                return false;
            }
        }
        return true;
    }


    @Override
    protected void readInputAsString(IndentedWriter writer, PropertyContext propertyContext)
    {
        String jsonValue = getOrSelectJson(writer, propertyContext);
        writer.write("if (%s != null) {", jsonValue);
        writer.indent();
        writer.write("if (%s.isNull() == null) {", jsonValue);
        writer.indent();
        String jsonString = propertyContext.getVariableNames().newVariableName("AsJsonString");
        writer.write("JSONString %s = %s.isString();", jsonString, jsonValue);
        writer.write("if (%s != null) {", jsonString);
        writer.indent();
        writer.write("String %s = %s.stringValue();", propertyContext.getVariableNames().getValueAsStringVariable(),
                jsonString);
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
        writer.write("%s.append(\"\\\"%s\\\":\");", propertyContext.getVariableNames().getBuilderVariable(),
                propertyContext.getPath());
    }


    @Override
    protected void writeValueAsString(IndentedWriter writer, PropertyContext propertyContext)
    {
        writer.write("%s.append(JsonUtils.escapeValue(%s));", propertyContext.getVariableNames().getBuilderVariable(),
                propertyContext.getVariableNames().getValueAsStringVariable());
    }


    @Override
    protected void writeValueDirectly(IndentedWriter writer, PropertyContext propertyContext)
    {
        if (propertyContext.getType().isPrimitive() == null)
        {
            // if null, append default value
            writer.write("if (%s == null) {", propertyContext.getVariableNames().getValueVariable());
            writer.indent();
            writer.write("%s.append(\"%s\");", propertyContext.getVariableNames().getBuilderVariable(), defaultValue());
            writer.outdent();
            writer.write("}");
            writer.write("else {");
            writer.indent();
            writer.write("%s.append(%s);", propertyContext.getVariableNames().getBuilderVariable(), propertyContext
                    .getVariableNames().getValueVariable());
            writer.outdent();
            writer.write("}");
        }
        else
        {
            writer.write("%s.append(%s);", propertyContext.getVariableNames().getBuilderVariable(), propertyContext
                    .getVariableNames().getValueVariable());
        }
    }


    /**
     * Empty implementation
     * 
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#markupEnd(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.PropertyContext)
     */
    @Override
    public void markupEnd(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
    }


    // --------------------------------------------------------- helper methods

    /**
     * Return <code>true</code> if the path contains {@link #JSON_PATH_SYMBOLS},
     * <code>false</code> otherwise.
     * 
     * @param path
     * @return <code>true</code> if the path contains {@link #JSON_PATH_SYMBOLS}
     *         , <code>false</code> otherwise.
     */
    protected boolean isJsonPath(String path)
    {
        return StringUtils.containsAny(path, JSON_PATH_SYMBOLS);
    }


    /**
     * If {@link PropertyContext#getPath()} is not <code>null</code> get or
     * select data from the {@link VariableNames#getInputVariable()}, otherwise
     * assign the json data to {@link VariableNames#getInputVariable()}.
     * 
     * @param writer
     * @param propertyContext
     * @return the variable name containing the json value.
     */
    protected String getOrSelectJson(IndentedWriter writer, PropertyContext propertyContext)
    {
        // If there's a path then get the JSON value using this path,
        // otherwise it is expected that the JSON value is the inputVariable
        // itself (e.g. an array of strings has no path information for the
        // array elements)
        String jsonValue = propertyContext.getVariableNames().newVariableName("AsJsonValue");
        if (propertyContext.getPath() != null)
        {
            if (JsonUtils.isJsonPath(propertyContext.getPath()))
            {
                writer.write("JSONValue %s = JsonPath.select(%s, \"%s\");", jsonValue, propertyContext
                        .getVariableNames().getInputVariable(), propertyContext.getPath());
            }
            else
            {
                writer.write("JSONValue %s = %s.get(\"%s\");", jsonValue, propertyContext.getVariableNames()
                        .getInputVariable(), propertyContext.getPath());
            }
        }
        else
        {
            writer.write("JSONValue %s = %s;", jsonValue, propertyContext.getVariableNames().getInputVariable());
        }
        return jsonValue;
    }
}
