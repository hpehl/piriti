package name.pehl.piriti.rebind.propertyhandler;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.TypeUtils;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JType;

/**
 * {@link PropertyHandler} for arrays.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public abstract class AbstractCollectionPropertyHandler extends AbstractPropertyHandler
{
    public AbstractCollectionPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    protected abstract JType getElementType(PropertyContext propertyContext);


    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param propertyContext
     * @return
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractArrayPropertyHandler#isValid(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        JType elementType = getElementType(propertyContext);
        if (elementType == null)
        {
            skipProperty(writer, propertyContext, "No element type found");
            return false;
        }
        if (elementType.isArray() != null)
        {
            skipProperty(writer, propertyContext, "Multi-dimensional arrays / collections are not supported");
            return false;
        }
        if (TypeUtils.isCollection(elementType) || TypeUtils.isMap(elementType))
        {
            skipProperty(writer, propertyContext, "Arrays or collections of collections / maps are not supported");
            return false;
        }
        return true;
    }
}
