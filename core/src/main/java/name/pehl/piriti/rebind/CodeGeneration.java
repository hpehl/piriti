package name.pehl.piriti.rebind;

import name.pehl.piriti.rebind.fieldhandler.AssignmentPolicy;
import name.pehl.piriti.rebind.fieldhandler.FieldContext;

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
    public static void writeReaderInitialization(IndentedWriter writer, JClassType type)
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
     * Writes the assignment based on the {@link AssignmentPolicy} in the
     * {@code fieldContext}.
     * 
     * @param writer
     * @param fieldContext
     */
    public static void writeAssignement(IndentedWriter writer, FieldContext fieldContext)
    {
        writer.write("if (%s != null) {", fieldContext.getValueVariable());
        writer.indent();
        AssignmentPolicy assignmentPolicy = fieldContext.getAssignmentPolicy();
        switch (assignmentPolicy)
        {
            case FIELD_ONLY:
                if (!assignField(writer, fieldContext))
                {
                    String reason = String.format("Cannot assign %s to model.%s: Field is not accessible.",
                            fieldContext.getValueVariable(), fieldContext.getFieldName());
                    skipField(writer, fieldContext, reason);
                }
                break;
            case FIELD_FIRST:
                if (!assignField(writer, fieldContext))
                {
                    if (!assignSetter(writer, fieldContext))
                    {
                        String reason = String
                                .format("Can neither assign %1$s to model.%s, nor call model.%s(%1$s): Field is not accessible and setter is not available / accessible.",
                                        fieldContext.getValueVariable(), fieldContext.getFieldName(),
                                        setter(fieldContext.getFieldName()));
                        skipField(writer, fieldContext, reason);
                    }
                }
                break;
            case SETTER_ONLY:
                if (!assignSetter(writer, fieldContext))
                {
                    String reason = String.format("Setter is not available / not accessible.",
                            setter(fieldContext.getFieldName()), fieldContext.getValueVariable());
                    skipField(writer, fieldContext, reason);
                }
                break;
            case SETTER_FIRST:
                if (!assignSetter(writer, fieldContext))
                {
                    if (!assignField(writer, fieldContext))
                    {
                        String reason = String
                                .format("Can neither call model.%s(%2$s) nor assign %2$s to model.%s: Setter is not available / accessible and field is not accessible",
                                        setter(fieldContext.getFieldName()), fieldContext.getValueVariable(),
                                        fieldContext.getFieldName());
                        skipField(writer, fieldContext, reason);
                    }
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


    /**
     * Generates code comments if a field was skipped (contains the reason why
     * the field was skipped)
     * 
     * @param writer
     * @param fieldContext
     * @param reason
     */
    public static void skipField(IndentedWriter writer, FieldContext fieldContext, String reason)
    {
        writer.write("// Skipping field %s", fieldContext);
        writer.write("// " + reason);
        GWT.log("Skipping field " + fieldContext, null);
        GWT.log(reason, null);
    }


    // ------------------------------------------------------ internal methods

    private static boolean assignField(IndentedWriter writer, FieldContext fieldContext)
    {
        if (TypeUtils.isFieldAccessible(fieldContext.getModelType(), fieldContext.getFieldName()))
        {
            writer.write("model.%s = %s;", fieldContext.getFieldName(), fieldContext.getValueVariable());
            return true;
        }
        return false;
    }


    private static boolean assignSetter(IndentedWriter writer, FieldContext fieldContext)
    {
        String setter = setter(fieldContext.getFieldName());
        boolean accessible = TypeUtils.isMethodAccessible(fieldContext.getModelType(), setter,
                fieldContext.getFieldType());
        if (!accessible && fieldContext.isPrimitive())
        {
            // Try with primitive parameter...
            accessible = TypeUtils.isMethodAccessible(fieldContext.getModelType(), setter,
                    fieldContext.getPrimitiveType());
        }
        if (accessible)
        {
            writer.write("model.%s(%s);", setter, fieldContext.getValueVariable());
            return true;
        }
        return false;
    }


    private static void assignGxt(IndentedWriter writer, FieldContext fieldContext)
    {
        writer.write("model.set(\"%s\", %s);", fieldContext.getFieldName(), fieldContext.getValueVariable());
    }


    private static String setter(String fieldName)
    {
        String setter = fieldName;
        if (fieldName != null && fieldName.length() > 0)
        {
            setter = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        }
        return setter;
    }
}
