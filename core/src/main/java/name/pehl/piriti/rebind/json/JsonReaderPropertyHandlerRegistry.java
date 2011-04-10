package name.pehl.piriti.rebind.json;

import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.typeinfo.JType;

/**
 * {@link PropertyHandlerRegistry} used by the {@link JsonReaderCreator}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 136 $
 */
public class JsonReaderPropertyHandlerRegistry extends JsonPropertyHandlerRegistry
{
    public JsonReaderPropertyHandlerRegistry(TreeLogger logger)
    {
        super(logger);
    }


    /**
     * Looks up a property handler based on the information provided in the
     * property context. The lookup logic is implemented like this:
     * <ol>
     * <li>if the properties type is an enum return
     * {@link #newEnumFieldHandler()}
     * <li>If the properties type is an array return
     * {@link #newArrayFieldHandler()}
     * <li>If there's a format specified in the annotation and the type is int
     * Integer, long, Long, float, Float, double or Double return
     * {@link #newConverterFieldHandler()}
     * <li>Try to lookup the property handler by the properties type classname
     * (this will resolve all types registered in
     * {@link #registerInitialPropertyHandlers()}
     * <li>Return {@link #newRegistryFieldHandler()}
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
            // If there's a format or converter specified in the annotation and
            // the type is int, long, float or double use the converter to
            // convert the value, which is expected to be a string!
            JType fieldType = propertyContext.getType();
            if ((propertyContext.getFormat() != null || propertyContext.useCustomConverter())
                    && (TypeUtils.isInteger(fieldType) || TypeUtils.isLong(fieldType) || TypeUtils.isFloat(fieldType) || TypeUtils
                            .isDouble(fieldType)))
            {
                handler = newConverterFieldHandler();
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
        }
        return handler;
    }
}
