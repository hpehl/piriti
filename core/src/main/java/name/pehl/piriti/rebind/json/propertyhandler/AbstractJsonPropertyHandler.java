package name.pehl.piriti.rebind.json.propertyhandler;

import java.util.logging.Level;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.VariableNames;
import name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;

import org.apache.commons.lang.StringUtils;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.json.client.JSONValue;

/**
 * Abstract base class for {@linkplain PropertyHandler}s used for JSON
 * (de)serialization.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public abstract class AbstractJsonPropertyHandler extends AbstractPropertyHandler
{
    // -------------------------------------------------------------- constants

    protected static final String COLLECTION_ELEMENT_PATH = "special path marker for nested PropertyHandler";

    /**
     * JSONPath special characters.
     */
    protected static final char[] JSON_PATH_SYMBOLS = new char[] {'$', '@', '.', '[', ']', '*', '#', ',', ':', '?',
            '(', ')',};

    // -------------------------------------------------------- private members

    protected String jsonValueVariable;


    // ----------------------------------------------------------- constructors

    public AbstractJsonPropertyHandler(TreeLogger logger)
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
    public void declare(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        super.declare(writer, propertyContext);
        writer.write("String %s = null;", propertyContext.getVariableNames().getValueAsStringVariable());
        jsonValueVariable = propertyContext.getVariableNames().newVariableName("AsJsonValue");
        writer.write("JSONValue %s = null;", jsonValueVariable);
    }


    @Override
    protected void readInputNatively(IndentedWriter writer, PropertyContext propertyContext)
    {
        getOrSelectJson(writer, propertyContext);
        writer.write("if (%s != null) {", jsonValueVariable);
        writer.indent();
        JType type = propertyContext.getType();
        if (String.class.getName().equals(type.getQualifiedSourceName()))
        {
            writer.write("%s = %s.toString();", propertyContext.getVariableNames().getValueVariable(),
                    jsonValueVariable);
        }
        else if (type.isClassOrInterface() != null)
        {
            JClassType jsonValueType = propertyContext.getTypeContext().getTypeOracle()
                    .findType(JSONValue.class.getName());
            if (jsonValueType != null)
            {
                if (jsonValueType.equals(type.isClassOrInterface()))
                {
                    // Property is a JSONValue
                    CodeGeneration.log(writer, Level.FINE, "Mapping native property %s as JSONValue", propertyContext);
                    writer.write("%s = %s;", propertyContext.getVariableNames().getValueVariable(), jsonValueVariable);
                }
                else if (jsonValueType.isAssignableFrom(type.isClassOrInterface()))
                {
                    // Property is a subtype of JSONValue
                    CodeGeneration.log(writer, Level.FINE, "Mapping native property %s as subtype of JSONValue",
                            propertyContext);
                    writer.write("if (%s instanceof %s) {", jsonValueVariable, propertyContext.getType()
                            .getQualifiedSourceName());
                    writer.indent();
                    writer.write("%s = (%s)%s;", propertyContext.getVariableNames().getValueVariable(), propertyContext
                            .getType().getQualifiedSourceName(), jsonValueVariable);
                    writer.outdent();
                    writer.write("}");
                }
            }
        }
        writer.outdent();
        writer.write("}");
    }


    @Override
    protected void readInputAsString(IndentedWriter writer, PropertyContext propertyContext)
    {
        getOrSelectJson(writer, propertyContext);
        writer.write("if (%s != null) {", jsonValueVariable);
        writer.indent();
        writer.write("if (%s.isNull() == null) {", jsonValueVariable);
        writer.indent();
        String jsonString = propertyContext.getVariableNames().newVariableName("AsJsonString");
        writer.write("JSONString %s = %s.isString();", jsonString, jsonValueVariable);
        writer.write("if (%s != null) {", jsonString);
        writer.indent();
        writer.write("%s = %s.stringValue();", propertyContext.getVariableNames().getValueAsStringVariable(),
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
                propertyContext.getPathOrName());
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
        writer.write("%s.append(%s);", propertyContext.getVariableNames().getBuilderVariable(), propertyContext
                .getVariableNames().getValueVariable());
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
    protected void getOrSelectJson(IndentedWriter writer, PropertyContext propertyContext)
    {
        if (COLLECTION_ELEMENT_PATH.equals(propertyContext.getPath()))
        {
            writer.write("%s = %s;", jsonValueVariable, propertyContext.getVariableNames().getInputVariable());
        }
        else
        {
            if (isJsonPath(propertyContext.getPath()))
            {
                writer.write("%s = JsonPath.select(%s, \"%s\");", jsonValueVariable, propertyContext.getVariableNames()
                        .getInputVariable(), propertyContext.getPath());
            }
            else
            {
                writer.write("%s = %s.get(\"%s\");", jsonValueVariable, propertyContext.getVariableNames()
                        .getInputVariable(), propertyContext.getPathOrName());
            }
        }
    }
}
