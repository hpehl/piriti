package name.pehl.piriti.rebind.propertyhandler;

import name.pehl.piriti.rebind.PropertyContext;

/**
 * Interface to find {@link PropertyHandler}s based on a {@link PropertyContext}
 * .
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public interface PropertyHandlerLookup
{
    /**
     * Looks up a property handler based on the information provided in the
     * property context.
     * 
     * @param propertyContext
     * @return
     */
    PropertyHandler lookup(PropertyContext propertyContext);
}
