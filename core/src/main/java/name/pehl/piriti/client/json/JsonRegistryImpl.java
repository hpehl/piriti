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
    private Map<Class<?>, JsonReader<?>> readers;
    private Map<Class<?>, JsonWriter<?>> writers;


    public JsonRegistryImpl()
    {
        readers = new HashMap<Class<?>, JsonReader<?>>();
        writers = new HashMap<Class<?>, JsonWriter<?>>();
    }


    @Override
    public <T> void register(Class<T> clazz, JsonReader<T> reader)
    {
        readers.put(clazz, reader);
    }


    @Override
    public <T> void register(Class<T> clazz, JsonWriter<T> writer)
    {
        writers.put(clazz, writer);
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T> JsonReader<T> getReader(Class<T> clazz)
    {
        return (JsonReader<T>) readers.get(clazz);
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T> JsonWriter<T> getWriter(Class<T> clazz)
    {
        return (JsonWriter<T>) writers.get(clazz);
    }
}
