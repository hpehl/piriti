package name.pehl.piriti.rebind.propertyhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.TypeUtils;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JType;

/**
 * Abstract {@link PropertyHandler} for arrays.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public abstract class AbstractArrayPropertyHandler extends AbstractPropertyHandler
{
    /**
     * Returns <code>false</code> if the field type is no array, or if the
     * component type is another array, collection or map, <code>true</code>
     * otherwise.
     * 
     * @param writer
     * @param fieldContext
     * @return
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler#isValid(name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        if (!fieldContext.isArray())
        {
            CodeGeneration.skipField(writer, fieldContext, "Type is no array");
            return false;
        }
        JType componentType = fieldContext.getArrayType().getComponentType();
        if (componentType.isArray() != null)
        {
            CodeGeneration.skipField(writer, fieldContext, "Multi-dimensional arrays are not supported");
            return false;
        }
        if (TypeUtils.isCollection(componentType) || TypeUtils.isMap(componentType))
        {
            CodeGeneration.skipField(writer, fieldContext, "Arrays of collections / maps are not supported");
            return false;
        }
        return true;
    }
}
