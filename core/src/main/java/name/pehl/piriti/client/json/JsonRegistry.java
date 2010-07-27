package name.pehl.piriti.client.json;

/**
 * A registry for {@linkplain JsonReader}s and {@linkplain JsonWriter}s. All
 * readers and writers are registered against this registry. Thereby references
 * between POJOs can be mapped.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 8 $
 */
public interface JsonRegistry
{
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
    <T> void register(Class<T> clazz, JsonReader<T> reader);


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
    <T> void register(Class<T> clazz, JsonWriter<T> writer);


    /**
     * Returns the {@link JsonReader} for the specified type
     * 
     * @param <T>
     * @param clazz
     * @return The {@link JsonReader} or {@code null} if no {@link JsonReader}
     *         is found
     */
    <T> JsonReader<T> getReader(Class<T> clazz);


    /**
     * Returns the {@link JsonWriter} for the specified type
     * 
     * @param <T>
     * @param clazz
     * @return The {@link JsonWriter} or {@code null} if no {@link JsonWriter}
     *         is found
     */
    <T> JsonWriter<T> getWriter(Class<T> clazz);
}
