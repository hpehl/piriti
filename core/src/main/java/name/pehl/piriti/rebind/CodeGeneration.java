package name.pehl.piriti.rebind;

import name.pehl.piriti.property.client.NoopPropertyGetter;
import name.pehl.piriti.property.client.NoopPropertySetter;
import name.pehl.piriti.rebind.json.JsonPathUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JConstructor;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.core.ext.typeinfo.JType;

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


    // ----------------------------------------------------- assignment methods

    /**
     * Writes the assignment based on {@link PropertyContext#getPropertyStyle()}
     * . The assignement is only done if some value was read from JSON / XML.
     * 
     * @param writer
     * @param propertyContext
     */
    public static void assign(IndentedWriter writer, PropertyContext propertyContext)
    {
        writer.write("if (%s != null) {", propertyContext.getVariableNames().getValueVariable());
        writer.indent();
        if (propertyContext.getSetter() == null || propertyContext.getSetter() == NoopPropertySetter.class)
        {
            if (propertyContext.getTypeContext().isGxt())
            {
                assignGxt(writer, propertyContext);
            }
            else
            {
                assignFieldOrSetter(writer, propertyContext);
            }
        }
        else
        {
            writer.write("%1$s setter = GWT.create(%1$s.class);", propertyContext.getSetter().getName());
            writer.write("setter.set(model, %s);", propertyContext.getVariableNames().getValueVariable());
        }
        writer.outdent();
        writer.write("}");
    }


    private static void assignFieldOrSetter(IndentedWriter writer, PropertyContext propertyContext)
    {
        JField field = TypeUtils.findField(propertyContext.getTypeContext().getType(), propertyContext.getName());
        if (field == null)
        {
            String reason = String.format("Cannot assign %s: No field found in %s.", propertyContext.getName(),
                    propertyContext.getTypeContext().getType().getQualifiedSourceName());
            skipProperty(writer, propertyContext, reason);
        }

        JClassType enclosingType = field.getEnclosingType();
        boolean samePackage = propertyContext.getTypeContext().getRwType().getPackage() == enclosingType.getPackage();
        if (!field.isFinal() && (field.isPublic() || samePackage && (field.isDefaultAccess() || field.isProtected())))
        {
            writer.write("model.%s = %s;", propertyContext.getName(), propertyContext.getVariableNames()
                    .getValueVariable());
        }
        else
        {
            JMethod setter = null;
            JType parameter = propertyContext.isPrimitive() ? propertyContext.getPrimitiveType() : propertyContext
                    .getType();
            if (samePackage)
            {
                setter = TypeUtils.findSetter(propertyContext.getTypeContext().getType(), propertyContext.getName(),
                        parameter, Modifier.DEFAULT, Modifier.PROTECTED, Modifier.PUBLIC);
            }
            else
            {
                setter = TypeUtils.findSetter(propertyContext.getTypeContext().getType(), propertyContext.getName(),
                        parameter, Modifier.PUBLIC);
            }
            if (setter != null)
            {
                writer.write("model.%s(%s);", setter.getName(), propertyContext.getVariableNames().getValueVariable());
            }
            else
            {
                String reason = String.format("Cannot assign %s: No accessible field or setter found in %s.",
                        propertyContext.getName(), propertyContext.getTypeContext().getType().getQualifiedSourceName());
                skipProperty(writer, propertyContext, reason);
            }
        }
    }


    private static void assignGxt(IndentedWriter writer, PropertyContext propertyContext)
    {
        writer.write("model.set(\"%s\", %s);", propertyContext.getName(), propertyContext.getVariableNames()
                .getValueVariable());
    }


    // ------------------------------------------------------------- read field

    public static void readField(IndentedWriter writer, PropertyContext propertyContext)
    {
        if (propertyContext.getGetter() == null || propertyContext.getGetter() == NoopPropertyGetter.class)
        {
            if (propertyContext.getTypeContext().isGxt())
            {
                readGxt(writer, propertyContext);
            }
            else
            {
                readFieldOrSetter(writer, propertyContext);
            }
        }
        else
        {
            writer.write("%1$s getter = GWT.create(%1$s.class);", propertyContext.getGetter().getName());
            writer.write("%s = getter.get(model);", propertyContext.getVariableNames().getValueVariable());
        }
    }


    private static void readFieldOrSetter(IndentedWriter writer, PropertyContext propertyContext)
    {
        JField field = TypeUtils.findField(propertyContext.getTypeContext().getType(), propertyContext.getName());
        if (field == null)
        {
            String reason = String.format("Cannot read %s: No accessible field found in %s.",
                    propertyContext.getName(), propertyContext.getTypeContext().getType().getQualifiedSourceName());
            skipProperty(writer, propertyContext, reason);
        }

        JClassType enclosingType = field.getEnclosingType();
        boolean samePackage = propertyContext.getTypeContext().getRwType().getPackage() == enclosingType.getPackage();
        if (!field.isFinal() && (field.isPublic() || samePackage && (field.isDefaultAccess() || field.isProtected())))
        {
            writer.write("%s = model.%s;", propertyContext.getVariableNames().getValueVariable(),
                    propertyContext.getName());
        }
        else
        {
            JMethod getter = null;
            JType returnType = propertyContext.isPrimitive() ? propertyContext.getPrimitiveType() : propertyContext
                    .getType();
            if (samePackage)
            {
                getter = TypeUtils.findGetter(propertyContext.getTypeContext().getType(), propertyContext.getName(),
                        returnType, Modifier.DEFAULT, Modifier.PROTECTED, Modifier.PUBLIC);
            }
            else
            {
                getter = TypeUtils.findGetter(propertyContext.getTypeContext().getType(), propertyContext.getName(),
                        returnType, Modifier.PUBLIC);
            }
            if (getter != null)
            {
                writer.write("%s = model.%s();", propertyContext.getVariableNames().getValueVariable(),
                        getter.getName());
            }
            else
            {
                String reason = String.format("Cannot read %s: No accessible field or setter found in %s.",
                        propertyContext.getName(), propertyContext.getTypeContext().getType().getQualifiedSourceName());
                skipProperty(writer, propertyContext, reason);
            }
        }
    }


    private static void readGxt(IndentedWriter writer, PropertyContext propertyContext)
    {
        writer.write("%s = model.get(\"%s\");", propertyContext.getVariableNames().getValueVariable(),
                propertyContext.getName());
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
            if (JsonPathUtils.isJsonPath(propertyContext.getPath()))
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


    /**
     * Generates code comments if a property was skipped (contains the reason
     * why the field was skipped)
     * 
     * @param writer
     * @param propertyContext
     * @param reason
     */
    public static void skipProperty(IndentedWriter writer, PropertyContext propertyContext, String reason)
    {
        writer.write("// Skipping property %s", propertyContext);
        writer.write("// " + reason);
        GWT.log("Skipping property " + propertyContext, null);
        GWT.log(reason, null);
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
