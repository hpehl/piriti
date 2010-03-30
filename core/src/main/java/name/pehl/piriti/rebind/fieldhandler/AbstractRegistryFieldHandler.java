package name.pehl.piriti.rebind.fieldhandler;

import name.pehl.piriti.rebind.FieldContext;
import name.pehl.piriti.rebind.IndentedWriter;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;

/**
 * Abstract {@link FieldHandler} implementation for types with an own reader.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public abstract class AbstractRegistryFieldHandler extends AbstractFieldHandler
{
    /**
     * Returns <code>true</code> if the field type is a class or interface and
     * if there's a public static field of type {@link #getReaderClassname()} in
     * the field type, <code>false</code> otherwise.
     * 
     * @param writer
     * @param fieldContext
     * @return
     * @see name.pehl.piriti.rebind.fieldhandler.AbstractFieldHandler#isValid(name.pehl.piriti.rebind.FieldContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        if (!fieldContext.isClassOrInterface())
        {
            skipField(writer, fieldContext, "Type is no class or interface");
            return false;
        }
        JField registryField = findReaderMember(fieldContext.getClassOrInterfaceType());
        if (registryField == null)
        {
            skipField(writer, fieldContext, String.format("No public static field of type %1$s<%2$s> found in %2$s",
                    getReaderClassname(), fieldContext.getFieldType().getQualifiedSourceName()));
            return false;
        }
        return true;
    }


    protected JField findReaderMember(JClassType type)
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
                        JClassType[] interfazes = fieldType.getImplementedInterfaces();
                        if (interfazes != null)
                        {
                            for (JClassType interfaze : interfazes)
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
