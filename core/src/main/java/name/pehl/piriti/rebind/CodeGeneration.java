package name.pehl.piriti.rebind;

import java.util.logging.Level;

import name.pehl.piriti.commons.client.WhitespaceHandling;
import name.pehl.piriti.rebind.json.JsonUtils;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JConstructor;
import com.google.gwt.core.ext.typeinfo.JParameter;

/**
 * Contains utility method for the code generation
 * 
 * @author $Author$
 * @version $Date$ $Revision: 527
 *          $
 */
public final class CodeGeneration
{
    /**
     * Private constructor to ensure that the class acts as a true utility class
     * i.e. it isn't instantiable and extensible.
     */
    private CodeGeneration()
    {
    }


    // -------------------------------------------------------------- converter

    public static void useConverterForReading(IndentedWriter writer, PropertyContext propertyContext)
    {
        String converterVariable = propertyContext.getVariableNames().newVariableName("ReadConverter");
        writer.write("Converter<%1$s> %2$s = GWT.create(%3$s.class);", propertyContext.getType()
                .getQualifiedSourceName(), converterVariable, propertyContext.getConverter().getName());
        writer.write("%s = %s.convert(%s, %s);", propertyContext.getVariableNames().getValueVariable(),
                converterVariable, propertyContext.getVariableNames().getValueAsStringVariable(),
                propertyContext.getFormat() == null ? "null" : "\"" + propertyContext.getFormat() + "\"");
    }


    public static void useConverterForWriting(IndentedWriter writer, PropertyContext propertyContext)
    {
        String converterVariable = propertyContext.getVariableNames().newVariableName("WriteConverter");
        writer.write("Converter<%1$s> %2$s = GWT.create(%3$s.class);", propertyContext.getType()
                .getQualifiedSourceName(), converterVariable, propertyContext.getConverter().getName());
        // TODO Check JsonUtils.escape() parameter for null!
        String convertedValue = propertyContext.getVariableNames().newVariableName("ConvertedValue");
        writer.write("String %s = %s.serialize(%s, %s);", convertedValue, converterVariable, propertyContext
                .getVariableNames().getValueVariable(), propertyContext.getFormat() == null ? "null" : "\""
                + propertyContext.getFormat() + "\"");
        writer.write("if (%s != null) {", convertedValue);
        writer.indent();
        writer.write("%s.append(JsonUtils.escapeValue(%s));", propertyContext.getVariableNames().getBuilderVariable(),
                convertedValue);
        writer.outdent();
        writer.write("}");
        writer.write("else {");
        writer.indent();
        writer.write("%s.append(\"null\");", propertyContext.getVariableNames().getBuilderVariable());
        writer.outdent();
        writer.write("}");
    }


    // ----------------------------------------------------------- misc methods

    public static void log(IndentedWriter writer, Level level, String message, Object... params)
    {
        String formatedMessage = String.format(message, params);
        writer.write("if (logger.isLoggable(%s)) {", level);
        writer.indent();
        writer.write("logger.log(%s, \"%s\");", level, formatedMessage);
        writer.outdent();
        writer.write("}");
    }


    /**
     * If {@link PropertyContext#getPath()} is not <code>null</code> get or
     * select data from the {@link VariableNames#getInputVariable()}, otherwise
     * assign the json data to {@link VariableNames#getInputVariable()}.
     * 
     * @param writer
     * @param propertyContext
     * @return the variable name containing the json data.
     */
    public static String getOrSelectJson(IndentedWriter writer, PropertyContext propertyContext)
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


    public static String getOrSelectXml(IndentedWriter writer, PropertyContext propertyContext)
    {
        String path = propertyContext.getPath();
        if (path == null)
        {

        }
        else
        {

        }

        writer.write("String %s = %s.selectValue(\"%s\", %s);", propertyContext.getVariableNames()
                .getValueAsStringVariable(), propertyContext.getVariableNames().getInputVariable(), path,
                propertyContext.getWhitespaceHandling() == WhitespaceHandling.REMOVE);
        writer.write("String %s = %s.selectValue(\"%s\", %s);", propertyContext.getVariableNames()
                .getValueAsStringVariable(), propertyContext.getVariableNames().getInputVariable(), path,
                propertyContext.getWhitespaceHandling() == WhitespaceHandling.REMOVE);
        writer.write("String %s = %s.selectValue(\"%s\", %s);", propertyContext.getVariableNames()
                .getValueAsStringVariable(), propertyContext.getVariableNames().getInputVariable(), path,
                propertyContext.getWhitespaceHandling() == WhitespaceHandling.REMOVE);
        writer.write("%s = %s.selectValue(\"%s\", %s);", propertyContext.getVariableNames().getValueVariable(),
                propertyContext.getVariableNames().getInputVariable(), path,
                propertyContext.getWhitespaceHandling() == WhitespaceHandling.REMOVE);

        String jsonValue = propertyContext.getVariableNames().newVariableName("AsJsonValue");
        if (path != null)
        {
            if (JsonUtils.isJsonPath(path))
            {
                writer.write("JSONValue %s = JsonPath.select(%s, \"%s\");", jsonValue, propertyContext
                        .getVariableNames().getInputVariable(), path);
            }
            else
            {
                writer.write("JSONValue %s = %s.get(\"%s\");", jsonValue, propertyContext.getVariableNames()
                        .getInputVariable(), path);
            }
        }
        else
        {
            writer.write("JSONValue %s = %s;", jsonValue, propertyContext.getVariableNames().getInputVariable());
        }
        return jsonValue;
    }


    public static void idRef(IndentedWriter writer, JClassType type)
    {
        writer.write("public %s idRef(String id) {", type.getQualifiedSourceName());
        writer.indent();
        writer.write("return this.idMap.get(id);");
        writer.outdent();
        writer.write("}");
    }


    /**
     * To ensure all necessary reader and writer are registered, this little
     * helper method genereates a new instance of the specified type (only if
     * the type provides a noarg constructor).
     * 
     * <pre>
     * new &lt;specified type&gt;();
     * </pre>
     * 
     * @param writer
     * @param type
     */
    public static void readerWriterInitialization(IndentedWriter writer, JClassType type)
    {
        boolean noargConstructor = false;
        JConstructor[] constructors = type.getConstructors();
        if (constructors != null)
        {
            for (JConstructor constructor : constructors)
            {
                JParameter[] parameters = constructor.getParameters();
                if (parameters == null || parameters.length == 0)
                {
                    noargConstructor = true;
                    break;
                }
            }
        }
        else
        {
            noargConstructor = true;
        }
        if (noargConstructor)
        {
            writer.write(
                    "new %1$s(); // if there are any reader definitions in %1$s, this ensures they are registered",
                    type.getParameterizedQualifiedSourceName());
        }
    }


    // ----------------------------------------------------------- json methods

    /**
     * Appends the properties name in quotes and the colon to the StringBuilder
     * holding the JSON serialization data.
     * 
     * @param writer
     * @param propertyContext
     */
    public static void appendJsonKey(IndentedWriter writer, PropertyContext propertyContext)
    {
        writer.write("%s.append(\"\\\"%s\\\":\");", propertyContext.getVariableNames().getBuilderVariable(),
                propertyContext.getPath());
    }


    /**
     * Appends {@link VariableNames#getValueVariable()} to the StringBuilder
     * holding the JSON serialization data. Use this method only for non-null
     * values of {@link VariableNames#getValueVariable()}.
     * 
     * @param writer
     * @param propertyContext
     * @param asString
     *            Whether to quote the value.
     */
    public static void appendJsonValue(IndentedWriter writer, PropertyContext propertyContext, boolean asString)
    {
        if (propertyContext.useCustomConverter())
        {
            useConverterForWriting(writer, propertyContext);
        }
        else if (asString)
        {
            writer.write("%s.append(JsonUtils.escapeValue(String.valueOf(%s)));", propertyContext.getVariableNames()
                    .getBuilderVariable(), propertyContext.getVariableNames().getValueVariable());
        }
        else
        {
            writer.write("%s.append(%s);", propertyContext.getVariableNames().getBuilderVariable(), propertyContext
                    .getVariableNames().getValueVariable());
        }
    }
}
