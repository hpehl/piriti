package name.pehl.piriti.rebind;

import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.VariableNames;

import com.google.gwt.core.client.GWT;
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
        switch (propertyContext.getPropertyStyle())
        {
            case FIELD:
                if (TypeUtils.isFieldAccessible(propertyContext.getClazz(), propertyContext.getName(), false))
                {
                    writer.write("model.%s = %s;", propertyContext.getName(), propertyContext.getVariableNames()
                            .getValueVariable());
                }
                else
                {
                    String reason = String.format("Cannot assign %s to model.%s: Field is not accessible.",
                            propertyContext.getVariableNames().getValueVariable(), propertyContext.getName());
                    skipProperty(writer, propertyContext, reason);
                }
                break;
            // TODO Implement setter
            // case GETTER_SETTER:
            // if (isSetterAccessible(propertyContext))
            // {
            // writer.write("model.%s(%s);",
            // TypeUtils.buildSetter(propertyContext.getName()), propertyContext
            // .getVariableNames().getValueVariable());
            // }
            // else
            // {
            // String reason =
            // String.format("Setter is not available / not accessible.",
            // TypeUtils
            // .buildSetter(propertyContext.getName()),
            // propertyContext.getVariableNames()
            // .getValueVariable());
            // skipProperty(writer, propertyContext, reason);
            // }
            // break;
            case GXT:
                assignGxt(writer, propertyContext);
                break;
            default:
                break;
        }
        writer.outdent();
        writer.write("}");
    }


    private static void assignGxt(IndentedWriter writer, PropertyContext propertyContext)
    {
        writer.write("model.set(\"%s\", %s);", propertyContext.getName(), propertyContext.getVariableNames()
                .getValueVariable());
    }


    private static boolean isSetterAccessible(PropertyContext propertyContext)
    {
        boolean accessible = false;
        if (propertyContext.getPrimitiveType() != null)
        {
            // Autoboxing: First try with primitive type
            accessible = TypeUtils.isSetterAccessible(propertyContext.getClazz(), propertyContext.getName(),
                    propertyContext.getPrimitiveType());
        }
        if (!accessible)
        {
            // Fall back to fieldContext.getType()
            accessible = TypeUtils.isSetterAccessible(propertyContext.getClazz(), propertyContext.getName(),
                    propertyContext.getType());
        }
        return accessible;
    }


    // ------------------------------------------------------------- read field

    public static void readField(IndentedWriter writer, PropertyContext propertyContext)
    {
        switch (propertyContext.getPropertyStyle())
        {
            case FIELD:
                if (TypeUtils.isFieldAccessible(propertyContext.getClazz(), propertyContext.getName(), true))
                {
                    writer.write("%s = model.%s;", propertyContext.getVariableNames().getValueVariable(),
                            propertyContext.getName());
                }
                else
                {
                    String reason = String.format("Cannot read model.%s: Field is not accessible.", propertyContext
                            .getVariableNames().getValueVariable(), propertyContext.getName());
                    skipProperty(writer, propertyContext, reason);
                }
                break;
            // TODO Implement getter
            // case GETTER_SETTER:
            // if (TypeUtils.isGetterAccessible(propertyContext.getClazz(),
            // propertyContext.getName()))
            // {
            // writer.write("%s = model.%s();",
            // propertyContext.getVariableNames().getValueVariable(),
            // TypeUtils.buildGetter(propertyContext.getName()));
            // }
            // else if
            // (TypeUtils.isBooleanGetterAccessible(propertyContext.getClazz(),
            // propertyContext.getName()))
            // {
            // writer.write("%s = model.%s();",
            // propertyContext.getVariableNames().getValueVariable(),
            // TypeUtils.buildBooleanGetter(propertyContext.getName()));
            // }
            // else
            // {
            // String reason =
            // String.format("Cannot call model.%s(): Getter is not available / accessible.",
            // TypeUtils.buildGetter(propertyContext.getName()));
            // skipProperty(writer, propertyContext, reason);
            // }
            // break;
            case GXT:
                writer.write("%s = model.get(\"%s\");", propertyContext.getVariableNames().getValueVariable(),
                        propertyContext.getName());
                break;
            default:
                break;
        }
    }


    // ----------------------------------------------------------- misc methods

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
     * @param quote
     *            Whether to quote the value.
     */
    public static void appendJsonValue(IndentedWriter writer, PropertyContext propertyContext, boolean quote)
    {
        if (quote)
        {
            writer.write("%s.append(\"\\\"\");", propertyContext.getVariableNames().getBuilderVariable());
        }
        writer.write("%s.append(%s);", propertyContext.getVariableNames().getBuilderVariable(), propertyContext
                .getVariableNames().getValueVariable());
        if (quote)
        {
            writer.write("%s.append(\"\\\"\");", propertyContext.getVariableNames().getBuilderVariable());
        }
    }
}
