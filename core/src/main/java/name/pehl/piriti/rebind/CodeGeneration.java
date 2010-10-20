package name.pehl.piriti.rebind;

import name.pehl.piriti.rebind.propertyhandler.Assignment.AssignmentPolicy;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JConstructor;
import com.google.gwt.core.ext.typeinfo.JParameter;

/**
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
     * Writes the assignment based on the {@link AssignmentPolicy} in the
     * {@code fieldContext}. The assignement is only done if
     * {@link PropertyContext#getValueVariable()} is not null.
     * 
     * @param writer
     * @param propertyContext
     */
    public static void assign(IndentedWriter writer, PropertyContext propertyContext)
    {
        writer.write("if (%s != null) {", propertyContext.getVariableNames().getValueVariable());
        writer.indent();
        switch (propertyContext.getAssignment().getPolicy())
        {
            case FIELD_ONLY:
                if (TypeUtils.isFieldAccessible(propertyContext.getClazz(), propertyContext.getName(), false))
                {
                    writer.write("model.%s = %s;", propertyContext.getName(), propertyContext.getVariableNames()
                            .getValueVariable());
                }
                else
                {
                    String reason = String.format("Cannot assign %s to model.%s: Field is not accessible.",
                            propertyContext.getVariableNames().getValueVariable(), propertyContext.getName());
                    skipField(writer, propertyContext, reason);
                }
                break;
            case FIELD_FIRST:
                if (TypeUtils.isFieldAccessible(propertyContext.getClazz(), propertyContext.getName(), false))
                {
                    writer.write("model.%s = %s;", propertyContext.getName(), propertyContext.getVariableNames()
                            .getValueVariable());
                }
                else if (isSetterAccessible(propertyContext))
                {
                    writer.write("model.%s(%s);", TypeUtils.buildSetter(propertyContext.getName()), propertyContext
                            .getVariableNames().getValueVariable());
                }
                else
                {
                    String reason = String
                            .format("Can neither assign %1$s to model.%s, nor call model.%s(%1$s): Field and / or setter is not available / accessible.",
                                    propertyContext.getVariableNames().getValueVariable(), propertyContext.getName(),
                                    TypeUtils.buildSetter(propertyContext.getName()));
                    skipField(writer, propertyContext, reason);
                }
                break;
            case PROPERTY_ONLY:
                if (isSetterAccessible(propertyContext))
                {
                    writer.write("model.%s(%s);", TypeUtils.buildSetter(propertyContext.getName()), propertyContext
                            .getVariableNames().getValueVariable());
                }
                else
                {
                    String reason = String.format("Setter is not available / not accessible.", TypeUtils
                            .buildSetter(propertyContext.getName()), propertyContext.getVariableNames()
                            .getValueVariable());
                    skipField(writer, propertyContext, reason);
                }
                break;
            case PROPERTY_FIRST:
                if (isSetterAccessible(propertyContext))
                {
                    writer.write("model.%s(%s);", TypeUtils.buildSetter(propertyContext.getName()), propertyContext
                            .getVariableNames().getValueVariable());
                }
                else if (TypeUtils.isFieldAccessible(propertyContext.getClazz(), propertyContext.getName(), false))
                {
                    writer.write("model.%s = %s;", propertyContext.getName(), propertyContext.getVariableNames()
                            .getValueVariable());
                }
                else
                {
                    String reason = String
                            .format("Can neither call model.%s(%2$s) nor assign %2$s to model.%s: Setter and / or field is not available / accessible",
                                    TypeUtils.buildSetter(propertyContext.getName()), propertyContext
                                            .getVariableNames().getValueVariable(), propertyContext.getName());
                    skipField(writer, propertyContext, reason);
                }
                break;
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
            // Fall back to fieldContext.getFieldType()
            accessible = TypeUtils.isSetterAccessible(propertyContext.getClazz(), propertyContext.getName(),
                    propertyContext.getType());
        }
        return accessible;
    }


    // ------------------------------------------------------------- read field

    public static void readField(IndentedWriter writer, PropertyContext propertyContext)
    {
        switch (propertyContext.getAssignment().getPolicy())
        {
            case FIELD_ONLY:
                if (TypeUtils.isFieldAccessible(propertyContext.getClazz(), propertyContext.getName(), true))
                {
                    writer.write("%s = model.%s;", propertyContext.getVariableNames().getValueVariable(),
                            propertyContext.getName());
                }
                else
                {
                    String reason = String.format("Cannot read model.%s: Field is not accessible.", propertyContext
                            .getVariableNames().getValueVariable(), propertyContext.getName());
                    skipField(writer, propertyContext, reason);
                }
                break;
            case FIELD_FIRST:
                if (TypeUtils.isFieldAccessible(propertyContext.getClazz(), propertyContext.getName(), true))
                {
                    writer.write("%s = model.%s;", propertyContext.getVariableNames().getValueVariable(),
                            propertyContext.getName());
                }
                else if (TypeUtils.isGetterAccessible(propertyContext.getClazz(), propertyContext.getName()))
                {
                    writer.write("%s = model.%s();", propertyContext.getVariableNames().getValueVariable(),
                            TypeUtils.buildGetter(propertyContext.getName()));
                }
                else
                {
                    String reason = String
                            .format("Can neither read model.%s, nor call model.%s(): Field and / or getter is not available / accessible.",
                                    propertyContext.getName(), TypeUtils.buildGetter(propertyContext.getName()));
                    skipField(writer, propertyContext, reason);
                }
                break;
            case PROPERTY_ONLY:
                if (TypeUtils.isGetterAccessible(propertyContext.getClazz(), propertyContext.getName()))
                {
                    writer.write("%s = model.%s();", propertyContext.getVariableNames().getValueVariable(),
                            TypeUtils.buildGetter(propertyContext.getName()));
                }
                else
                {
                    String reason = String.format("Cannot call model.%s(): Getter is not available / accessible.",
                            TypeUtils.buildGetter(propertyContext.getName()));
                    skipField(writer, propertyContext, reason);
                }
                break;
            case PROPERTY_FIRST:
                if (TypeUtils.isGetterAccessible(propertyContext.getClazz(), propertyContext.getName()))
                {
                    writer.write("%s = model.%s();", propertyContext.getVariableNames().getValueVariable(),
                            TypeUtils.buildGetter(propertyContext.getName()));
                }
                else if (TypeUtils.isFieldAccessible(propertyContext.getClazz(), propertyContext.getName(), true))
                {
                    writer.write("%s = model.%s;", propertyContext.getVariableNames().getValueVariable(),
                            propertyContext.getName());
                }
                else
                {
                    String reason = String
                            .format("Can neither call model.%s() nor read model.%s: Getter Field and / or field is not available / accessible.",
                                    propertyContext.getName(), TypeUtils.buildGetter(propertyContext.getName()));
                    skipField(writer, propertyContext, reason);
                }
                break;
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
     * Generates code comments if a field was skipped (contains the reason why
     * the field was skipped)
     * 
     * @param writer
     * @param propertyContext
     * @param reason
     */
    public static void skipField(IndentedWriter writer, PropertyContext propertyContext, String reason)
    {
        writer.write("// Skipping field %s", propertyContext);
        writer.write("// " + reason);
        GWT.log("Skipping field " + propertyContext, null);
        GWT.log(reason, null);
    }


    // ----------------------------------------------------------- json methods

    /**
     * Appends the fields name in quotes and the colon to the StringBuilder
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
     * Appends {@link PropertyContext#getValueVariable()} to the StringBuilder
     * holding the JSON serialization data. Use this method only for non-null
     * values of {@link PropertyContext#getValueVariable()}.
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
