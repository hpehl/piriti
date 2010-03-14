package name.pehl.gwt.piriti.rebind.fieldhandler;

import name.pehl.gwt.piriti.rebind.FieldContext;
import name.pehl.gwt.piriti.rebind.IndentedWriter;
import name.pehl.gwt.piriti.rebind.TypeUtils;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JType;

/**
 * Abstract {@link FieldHandler} for arrays.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public abstract class AbstractArrayFieldHandler extends AbstractFieldHandler
{
    /**
     * Returns <code>false</code> if the field type is no array or if the
     * component type of the array equals the model type, <code>true</code>
     * otherwise.
     * 
     * @param writer
     * @param fieldContext
     * @return
     * @see name.pehl.gwt.piriti.rebind.fieldhandler.AbstractFieldHandler#isValid(name.pehl.gwt.piriti.rebind.FieldContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        if (!fieldContext.isArray())
        {
            skipField(writer, fieldContext, "Type is no array");
            return false;
        }
        JType componentType = fieldContext.getArrayType().getComponentType();
        if (componentType.equals(fieldContext.getModelType()))
        {
            skipField(writer, fieldContext, "Component type of the array equals the model type");
            return false;
        }
        if (componentType.isArray() != null)
        {
            skipField(writer, fieldContext, "Multi-dimensional arrays are not supported");
            return false;
        }
        if (TypeUtils.isCollection(componentType) || TypeUtils.isMap(componentType))
        {
            skipField(writer, fieldContext, "Arrays of collections / maps are not supported");
            return false;
        }
        return true;
    }
}
