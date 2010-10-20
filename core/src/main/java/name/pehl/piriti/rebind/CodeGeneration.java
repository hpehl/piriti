package name.pehl.piriti.rebind;

import name.pehl.piriti.rebind.propertyhandler.AssignmentPolicy;
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
     * @param fieldContext
     */
    public static void assign(IndentedWriter writer, PropertyContext fieldContext)
    {
        writer.write("if (%s != null) {", fieldContext.getValueVariable());
        writer.indent();
        AssignmentPolicy assignmentPolicy = fieldContext.getAssignmentPolicy();
        switch (assignmentPolicy)
        {
            case FIELD_ONLY:
                if (TypeUtils.isFieldAccessible(fieldContext.getModelType(), fieldContext.getFieldName(), false))
                {
                    writer.write("model.%s = %s;", fieldContext.getFieldName(), fieldContext.getValueVariable());
                }
                else
                {
                    String reason = String.format("Cannot assign %s to model.%s: Field is not accessible.",
                            fieldContext.getValueVariable(), fieldContext.getFieldName());
                    skipField(writer, fieldContext, reason);
                }
                break;
            case FIELD_FIRST:
                if (TypeUtils.isFieldAccessible(fieldContext.getModelType(), fieldContext.getFieldName(), false))
                {
                    writer.write("model.%s = %s;", fieldContext.getFieldName(), fieldContext.getValueVariable());
                }
                else if (isSetterAccessible(fieldContext))
                {
                    writer.write("model.%s(%s);", TypeUtils.buildSetter(fieldContext.getFieldName()),
                            fieldContext.getValueVariable());
                }
                else
                {
                    String reason = String
                            .format("Can neither assign %1$s to model.%s, nor call model.%s(%1$s): Field and / or setter is not available / accessible.",
                                    fieldContext.getValueVariable(), fieldContext.getFieldName(),
                                    TypeUtils.buildSetter(fieldContext.getFieldName()));
                    skipField(writer, fieldContext, reason);
                }
                break;
            case PROPERTY_ONLY:
                if (isSetterAccessible(fieldContext))
                {
                    writer.write("model.%s(%s);", TypeUtils.buildSetter(fieldContext.getFieldName()),
                            fieldContext.getValueVariable());
                }
                else
                {
                    String reason = String.format("Setter is not available / not accessible.",
                            TypeUtils.buildSetter(fieldContext.getFieldName()), fieldContext.getValueVariable());
                    skipField(writer, fieldContext, reason);
                }
                break;
            case PROPERTY_FIRST:
                if (isSetterAccessible(fieldContext))
                {
                    writer.write("model.%s(%s);", TypeUtils.buildSetter(fieldContext.getFieldName()),
                            fieldContext.getValueVariable());
                }
                else if (TypeUtils.isFieldAccessible(fieldContext.getModelType(), fieldContext.getFieldName(), false))
                {
                    writer.write("model.%s = %s;", fieldContext.getFieldName(), fieldContext.getValueVariable());
                }
                else
                {
                    String reason = String
                            .format("Can neither call model.%s(%2$s) nor assign %2$s to model.%s: Setter and / or field is not available / accessible",
                                    TypeUtils.buildSetter(fieldContext.getFieldName()),
                                    fieldContext.getValueVariable(), fieldContext.getFieldName());
                    skipField(writer, fieldContext, reason);
                }
                break;
            case GXT:
                assignGxt(writer, fieldContext);
                break;
            default:
                break;
        }
        writer.outdent();
        writer.write("}");
    }


    private static void assignGxt(IndentedWriter writer, PropertyContext fieldContext)
    {
        writer.write("model.set(\"%s\", %s);", fieldContext.getFieldName(), fieldContext.getValueVariable());
    }


    private static boolean isSetterAccessible(PropertyContext fieldContext)
    {
        boolean accessible = false;
        if (fieldContext.getPrimitiveType() != null)
        {
            // Autoboxing: First try with primitive type
            accessible = TypeUtils.isSetterAccessible(fieldContext.getModelType(), fieldContext.getFieldName(),
                    fieldContext.getPrimitiveType());
        }
        if (!accessible)
        {
            // Fall back to fieldContext.getFieldType()
            accessible = TypeUtils.isSetterAccessible(fieldContext.getModelType(), fieldContext.getFieldName(),
                    fieldContext.getFieldType());
        }
        return accessible;
    }


    // ------------------------------------------------------------- read field

    public static void readField(IndentedWriter writer, PropertyContext fieldContext)
    {
        AssignmentPolicy assignmentPolicy = fieldContext.getAssignmentPolicy();
        switch (assignmentPolicy)
        {
            case FIELD_ONLY:
                if (TypeUtils.isFieldAccessible(fieldContext.getModelType(), fieldContext.getFieldName(), true))
                {
                    writer.write("%s = model.%s;", fieldContext.getValueVariable(), fieldContext.getFieldName());
                }
                else
                {
                    String reason = String.format("Cannot read model.%s: Field is not accessible.",
                            fieldContext.getValueVariable(), fieldContext.getFieldName());
                    skipField(writer, fieldContext, reason);
                }
                break;
            case FIELD_FIRST:
                if (TypeUtils.isFieldAccessible(fieldContext.getModelType(), fieldContext.getFieldName(), true))
                {
                    writer.write("%s = model.%s;", fieldContext.getValueVariable(), fieldContext.getFieldName());
                }
                else if (TypeUtils.isGetterAccessible(fieldContext.getModelType(), fieldContext.getFieldName()))
                {
                    writer.write("%s = model.%s();", fieldContext.getValueVariable(),
                            TypeUtils.buildGetter(fieldContext.getFieldName()));
                }
                else
                {
                    String reason = String
                            .format("Can neither read model.%s, nor call model.%s(): Field and / or getter is not available / accessible.",
                                    fieldContext.getFieldName(), TypeUtils.buildGetter(fieldContext.getFieldName()));
                    skipField(writer, fieldContext, reason);
                }
                break;
            case PROPERTY_ONLY:
                if (TypeUtils.isGetterAccessible(fieldContext.getModelType(), fieldContext.getFieldName()))
                {
                    writer.write("%s = model.%s();", fieldContext.getValueVariable(),
                            TypeUtils.buildGetter(fieldContext.getFieldName()));
                }
                else
                {
                    String reason = String.format("Cannot call model.%s(): Getter is not available / accessible.",
                            TypeUtils.buildGetter(fieldContext.getFieldName()));
                    skipField(writer, fieldContext, reason);
                }
                break;
            case PROPERTY_FIRST:
                if (TypeUtils.isGetterAccessible(fieldContext.getModelType(), fieldContext.getFieldName()))
                {
                    writer.write("%s = model.%s();", fieldContext.getValueVariable(),
                            TypeUtils.buildGetter(fieldContext.getFieldName()));
                }
                else if (TypeUtils.isFieldAccessible(fieldContext.getModelType(), fieldContext.getFieldName(), true))
                {
                    writer.write("%s = model.%s;", fieldContext.getValueVariable(), fieldContext.getFieldName());
                }
                else
                {
                    String reason = String
                            .format("Can neither call model.%s() nor read model.%s: Getter Field and / or field is not available / accessible.",
                                    fieldContext.getFieldName(), TypeUtils.buildGetter(fieldContext.getFieldName()));
                    skipField(writer, fieldContext, reason);
                }
                break;
            case GXT:
                writer.write("%s = model.get(\"%s\");", fieldContext.getValueVariable(), fieldContext.getFieldName());
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
     * To ensure all necessary readers are registered, this little helper method
     * genereates a new instance of the specified type (only if the type
     * provides a noarg constructor).
     * 
     * <pre>
     * new &lt;specified type&gt;();
     * </pre>
     * 
     * @param writer
     * @param type
     */
    public static void readerInitialization(IndentedWriter writer, JClassType type)
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
     * @param fieldContext
     * @param reason
     */
    public static void skipField(IndentedWriter writer, PropertyContext fieldContext, String reason)
    {
        writer.write("// Skipping field %s", fieldContext);
        writer.write("// " + reason);
        GWT.log("Skipping field " + fieldContext, null);
        GWT.log(reason, null);
    }


    // ----------------------------------------------------------- json methods

    /**
     * Appends the fields name in quotes and the colon to the StringBuilder
     * holding the JSON serialization data.
     * 
     * @param writer
     * @param fieldContext
     */
    public static void appendJsonKey(IndentedWriter writer, PropertyContext fieldContext)
    {
        writer.write("%s.append(\"\\\"%s\\\":\");", fieldContext.getBuilderVariable(), fieldContext.getPath());
    }


    /**
     * Appends {@link PropertyContext#getValueVariable()} to the StringBuilder
     * holding the JSON serialization data. Use this method only for non-null
     * values of {@link PropertyContext#getValueVariable()}.
     * 
     * @param writer
     * @param fieldContext
     * @param quote
     *            Whether to quote the value.
     */
    public static void appendJsonValue(IndentedWriter writer, PropertyContext fieldContext, boolean quote)
    {
        if (quote)
        {
            writer.write("%s.append(\"\\\"\");", fieldContext.getBuilderVariable());
        }
        writer.write("%s.append(%s);", fieldContext.getBuilderVariable(), fieldContext.getValueVariable());
        if (quote)
        {
            writer.write("%s.append(\"\\\"\");", fieldContext.getBuilderVariable());
        }
    }
}
