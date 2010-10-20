package name.pehl.piriti.rebind.json;

import name.pehl.piriti.rebind.propertyhandler.FieldContext;
import name.pehl.piriti.rebind.propertyhandler.FieldHandler;
import name.pehl.piriti.rebind.propertyhandler.FieldHandlerRegistry;

/**
 * {@link FieldHandlerRegistry} used by the {@link JsonWriterCreator}.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class JsonWriterFieldHandlerRegistry extends JsonFieldHandlerRegistry
{
    /**
     * Looks up a field handler based on the information provided in the field
     * context. The lookup logic is implemented like this:
     * <ol>
     * <li>if the fields type is an enum return {@link #newEnumFieldHandler()}
     * <li>If the fields type is an array return {@link #newArrayFieldHandler()}
     * <li>Try to lookup the field handler by the fields type classname (this
     * will resolve all types registered in
     * {@link #registerInitialFieldHandlers()}
     * <li>If no field handler return {@link #newRegistryFieldHandler()}
     * </ol>
     * 
     * @param fieldContext
     * @return
     * @see name.pehl.piriti.rebind.propertyhandler.FieldHandlerRegistry#findFieldHandler(name.pehl.piriti.rebind.propertyhandler.FieldContext)
     */
    public FieldHandler findFieldHandler(FieldContext fieldContext)
    {
        FieldHandler handler = null;
        if (fieldContext.isEnum())
        {
            handler = newEnumFieldHandler();
        }
        else if (fieldContext.isArray())
        {
            handler = newArrayFieldHandler();
        }
        else
        {
            // Ask the registry for all other stuff (basic types,
            // collections, maps, ...)
            handler = registry.get(fieldContext.getFieldType().getQualifiedSourceName());
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
