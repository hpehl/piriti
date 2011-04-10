package name.pehl.piriti.rebind.json;

import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;

import com.google.gwt.core.ext.TreeLogger;

/**
 * {@link PropertyHandlerRegistry} used by the {@link JsonWriterCreator}.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class JsonWriterPropertyHandlerRegistry extends JsonPropertyHandlerRegistry
{
    public JsonWriterPropertyHandlerRegistry(TreeLogger logger)
    {
        super(logger);
    }


    /**
     * Looks up a field handler based on the information provided in the field
     * context. The lookup logic is implemented like this:
     * <ol>
     * <li>if the fields type is an enum return {@link #newEnumFieldHandler()}
     * <li>If the fields type is an array return {@link #newArrayFieldHandler()}
     * <li>Try to lookup the field handler by the fields type classname (this
     * will resolve all types registered in
     * {@link #registerInitialPropertyHandlers()}
     * <li>If no field handler return {@link #newRegistryFieldHandler()}
     * </ol>
     * 
     * @param propertyContext
     * @return
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry#findPropertyHandler(name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public PropertyHandler findPropertyHandler(PropertyContext propertyContext)
    {
        PropertyHandler handler = null;
        if (propertyContext.isEnum())
        {
            handler = newEnumFieldHandler();
        }
        else if (propertyContext.isArray())
        {
            handler = newArrayFieldHandler();
        }
        else
        {
            // Ask the registry for all other stuff (basic types,
            // collections, maps, ...)
            handler = registry.get(propertyContext.getType().getQualifiedSourceName());
            if (handler == null)
            {
                // Delegate to the JsonRegistry to resolve other mapped
                // models
                handler = newRegistryFieldHandler();
            }
        }
        return handler;
    }
}
