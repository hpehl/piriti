package name.pehl.piriti.rebind.propertyhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.TypeUtils;

import com.google.gwt.core.ext.TreeLogger;
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
    public AbstractArrayPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    /**
     * Returns <code>false</code> if the properties type is no array, or if the
     * component type is another array, collection or map, <code>true</code>
     * otherwise.
     * 
     * @param writer
     * @param propertyContext
     * @return
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler#isValid(name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        if (!propertyContext.isArray())
        {
            CodeGeneration.skipProperty(writer, propertyContext, "Type is no array");
            return false;
        }
        JType componentType = propertyContext.getArrayType().getComponentType();
        if (componentType.isArray() != null)
        {
            CodeGeneration.skipProperty(writer, propertyContext, "Multi-dimensional arrays are not supported");
            return false;
        }
        if (TypeUtils.isCollection(componentType) || TypeUtils.isMap(componentType))
        {
            CodeGeneration.skipProperty(writer, propertyContext, "Arrays of collections / maps are not supported");
            return false;
        }
        return true;
    }
}
