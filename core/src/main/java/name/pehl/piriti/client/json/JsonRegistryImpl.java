package name.pehl.piriti.client.json;

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation for the {@link JsonRegistry}
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 8 $
 */
public class JsonRegistryImpl implements JsonRegistry
{
    private Map<Class<?>, JsonReader<?>> registry;


    public JsonRegistryImpl()
    {
        registry = new HashMap<Class<?>, JsonReader<?>>();
    }


    /**
     * Both the clazz and the reader must not be null.
     */
    @Override
    public <T> void register(Class<T> clazz, JsonReader<T> reader)
    {
        if (clazz != null && reader != null)
        {
            registry.put(clazz, reader);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> JsonReader<T> get(Class<T> clazz)
    {
        if (clazz != null)
        {
            return (JsonReader<T>) registry.get(clazz);
        }
        return null;
    }
}
