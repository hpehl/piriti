package name.pehl.gwt.piriti.rebind.fieldhandler;

import name.pehl.gwt.piriti.client.json.JsonReader;
import name.pehl.gwt.piriti.rebind.FieldContext;
import name.pehl.gwt.piriti.rebind.IndentedWriter;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;

/**
 * {@link FieldHandler} implementation for types with an own {@link JsonReader}.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public abstract class AbstractRegistryFieldHandler extends AbstractFieldHandler
{
    /**
     * Returns <code>true</code> if the field type is a class or interface and
     * if there's a public static field of type {@link JsonReader} in the field
     * type, <code>false</code> otherwise.
     * 
     * @param writer
     * @param fieldContext
     * @return
     * @see name.pehl.gwt.piriti.rebind.fieldhandler.AbstractFieldHandler#isValid(name.pehl.gwt.piriti.rebind.FieldContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        if (!fieldContext.isClassOrInterface())
        {
            skipField(writer, fieldContext, "Type is no class or interface");
            return false;
        }
        if (fieldContext.getModelType().equals(fieldContext.getFieldType()))
        {
            skipField(writer, fieldContext, "Field type must not be the same as the model type");
            return false;
        }
        JField registryField = findRegistryMember(fieldContext.getClassOrInterfaceType());
        if (registryField == null)
        {
            skipField(writer, fieldContext, String.format(
                    "No public static field of type JsonReader<%1$s> found in %1$s", fieldContext.getFieldType()
                            .getQualifiedSourceName()));
            return false;
        }
        return true;
    }


    protected JField findRegistryMember(JClassType type)
    {
        JField[] fields = type.getFields();
        if (fields != null)
        {
            for (JField field : fields)
            {
                if (field.isStatic() && field.isPublic())
                {
                    JClassType fieldType = field.getType().isClassOrInterface();
                    if (fieldType != null)
                    {
                        JClassType[] interfaces = fieldType.getImplementedInterfaces();
                        if (interfaces != null)
                        {
                            for (JClassType interfaze : interfaces)
                            {
                                if (getReaderClassname().equals(interfaze.getQualifiedSourceName()))
                                {
                                    return field;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }


    protected abstract String getReaderClassname();
}
