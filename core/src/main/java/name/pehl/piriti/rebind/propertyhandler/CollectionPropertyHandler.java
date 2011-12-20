package name.pehl.piriti.rebind.propertyhandler;

import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.TypeUtils;

import com.google.gwt.core.ext.typeinfo.JType;

/**
 * {@link PropertyHandler} for collections.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class CollectionPropertyHandler extends AbstractPropertyHandler
{
    @Override
    public boolean isValid(PropertyContext propertyContext)
    {
        if (!super.isValid(propertyContext))
        {
            return false;
        }
        JType elementType = TypeUtils.getTypeVariable(propertyContext.getType());
        if (elementType == null)
        {
            skipProperty(propertyContext, "No type parameter found");
            return false;
        }
        if (elementType.isArray() != null)
        {
            skipProperty(propertyContext, "Collections of arrays are not supported");
            return false;
        }
        if (TypeUtils.isCollection(elementType) || TypeUtils.isMap(elementType))
        {
            skipProperty(propertyContext, "Collections of collections / maps are not supported");
            return false;
        }
        PropertyContext nestedContext = new PropertyContext.Builder(propertyContext, elementType).build();
        PropertyHandler nestedHandler = new PropertyHandlerLookup().lookup(nestedContext);
        if (nestedHandler == null)
        {
            skipProperty(propertyContext, "No property handler found for element type" + nestedContext);
        }
        if (!nestedHandler.isValid(nestedContext))
        {
            skipProperty(propertyContext, "Element type " + nestedContext + " is not valid");
        }
        return true;
    }


    @Override
    public void setTemplate(PropertyContext propertyContext)
    {
        super.setTemplate(propertyContext);

        JType elementType = TypeUtils.getTypeVariable(propertyContext.getType());
        StringBuilder nestedTemplate = basePath(propertyContext);
        PropertyContext nestedContext = new PropertyContext.Builder(propertyContext, elementType).build();
        PropertyHandler nestedHandler = new PropertyHandlerLookup().lookup(nestedContext);
        nestedTemplate.append("Nested").append(nestedHandler.getClass().getSimpleName()).append(".vm");
        nestedContext.setTemplate(nestedTemplate.toString());
    }
}
