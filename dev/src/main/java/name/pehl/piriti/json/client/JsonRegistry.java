package name.pehl.piriti.json.client;

import java.util.HashMap;
import java.util.Map;

/**
 * A registry for {@linkplain JsonReader}s and {@linkplain JsonWriter}s. All
 * readers and writers are registered against this registry. Thereby references
 * between POJOs can be resolved.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 8 $
 */
public final class JsonRegistry
{
    private final Map<Class<?>, JsonReader<?>> readers;
    private final Map<Class<?>, JsonWriter<?>> writers;


    public JsonRegistry()
    {
        readers = new HashMap<Class<?>, JsonReader<?>>();
        writers = new HashMap<Class<?>, JsonWriter<?>>();
    }


    /**
     * Registers the {@link JsonReader} for the specified type
     * 
     * @param <T>
     *            The type
     * @param clazz
     *            The class
     * @param reader
     *            The {@link JsonReader}
     */
    public <T> void register(Class<T> clazz, JsonReader<T> reader)
    {
        readers.put(clazz, reader);
    }


    /**
     * Registers the {@link JsonWriter} for the specified type
     * 
     * @param <T>
     *            The type
     * @param clazz
     *            The class
     * @param writer
     *            The {@link JsonWriter}
     */
    public <T> void register(Class<T> clazz, JsonWriter<T> writer)
    {
        writers.put(clazz, writer);
    }


    /**
     * Returns the {@link JsonReader} for the specified type
     * 
     * @param <T>
     * @param clazz
     * @return The {@link JsonReader} or {@code null} if no {@link JsonReader}
     *         is found
     */
    @SuppressWarnings("unchecked")
    public <T> JsonReader<T> getReader(Class<T> clazz)
    {
        return (JsonReader<T>) readers.get(clazz);
    }


    /**
     * Returns the {@link JsonWriter} for the specified type
     * 
     * @param <T>
     * @param clazz
     * @return The {@link JsonWriter} or {@code null} if no {@link JsonWriter}
     *         is found
     */
    @SuppressWarnings("unchecked")
    public <T> JsonWriter<T> getWriter(Class<T> clazz)
    {
        return (JsonWriter<T>) writers.get(clazz);
    }
}
