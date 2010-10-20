package name.pehl.piriti.rebind.json;

import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.propertyhandler.FieldContext;
import name.pehl.piriti.rebind.propertyhandler.FieldHandler;
import name.pehl.piriti.rebind.propertyhandler.FieldHandlerRegistry;

import com.google.gwt.core.ext.typeinfo.JType;

/**
 * {@link FieldHandlerRegistry} used by the {@link JsonReaderCreator}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 136 $
 */
public class JsonReaderFieldHandlerRegistry extends JsonFieldHandlerRegistry
{
    /**
     * Looks up a field handler based on the information provided in the field
     * context. The lookup logic is implemented like this:
     * <ol>
     * <li>if the fields type is an enum return {@link #newEnumFieldHandler()}
     * <li>If the fields type is an array return {@link #newArrayFieldHandler()}
     * <li>If there's a format specified in the annotation and the type is int
     * Integer, long, Long, float, Float, double or Double return
     * {@link #newConverterFieldHandler()}
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
            // If there's a format specified in the annotation and
            // the type is int, long, float or double use the converter
            // to convert the value, which is expected to be a string!
            JType fieldType = fieldContext.getFieldType();
            if (fieldContext.getFormat() != null
                    && (TypeUtils.isInteger(fieldType) || TypeUtils.isLong(fieldType) || TypeUtils.isFloat(fieldType) || TypeUtils
                            .isDouble(fieldType)))
            {
                handler = newConverterFieldHandler();
            }
            else
            {
                // Ask the registry for all other stuff (basic types,
                // collections, maps, ...)
                handler = registry.get(fieldContext.getFieldType().getQualifiedSourceName());
                if (handler == null)
                {
                    // Delegate to the XmlRegistry to resolve other mapped
                    // models
                    handler = newRegistryFieldHandler();
                }
            }
        }
        return handler;
    }
}
