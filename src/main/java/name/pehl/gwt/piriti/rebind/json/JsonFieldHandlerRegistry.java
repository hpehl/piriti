package name.pehl.gwt.piriti.rebind.json;

import java.util.HashMap;
import java.util.Map;

import name.pehl.gwt.piriti.rebind.FieldContext;
import name.pehl.gwt.piriti.rebind.FieldHandler;
import name.pehl.gwt.piriti.rebind.FieldHandlerRegistry;

/**
 * {@link FieldHandlerRegistry} used by the {@link JsonReaderCreator}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 136 $
 */
public class JsonFieldHandlerRegistry implements FieldHandlerRegistry
{
    private Map<String, FieldHandler> registry;


    /**
     * Construct a new instance of this class and registers the initial field
     * handlers.
     */
    public JsonFieldHandlerRegistry()
    {
        registry = new HashMap<String, FieldHandler>();
        registerInitialFieldHandlers();
    }


    /**
     * Registers the initial field handler the json reader. The following
     * handlers are registered:
     * <ul>
     * <li>TODO Implement me
     * </ul>
     */
    private void registerInitialFieldHandlers()
    {
    }


    /**
     * Looks up a field handler based on the information provided in the field
     * context. The lookup logic is implemented like this:
     * <ol>
     * <li>TODO Implement me
     * </ol>
     * 
     * @param fieldContext
     * @return
     * @see name.pehl.gwt.piriti.rebind.FieldHandlerRegistry#findFieldHandler(name.pehl.gwt.piriti.rebind.FieldContext)
     */
    public FieldHandler findFieldHandler(FieldContext fieldContext)
    {
        FieldHandler handler = null;
        return handler;
    }
}
