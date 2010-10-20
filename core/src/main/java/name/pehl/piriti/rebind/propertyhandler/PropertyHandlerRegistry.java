package name.pehl.piriti.rebind.propertyhandler;


/**
 * Registry to find {@link PropertyHandler}s based on a {@link PropertyContext}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public interface PropertyHandlerRegistry
{
    /**
     * Looks up a field handler based on the information provided in the field
     * context.
     * 
     * @param fieldContext
     * @return
     */
    PropertyHandler findFieldHandler(PropertyContext fieldContext);
}
