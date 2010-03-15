package name.pehl.piriti.client.json;

/**
 * A registry for {@linkplain JsonReader}s. All {@linkplain JsonReader}s are
 * registered against this registry. This way nested types can be mapped.
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
     * Returns the {@link JsonReader} for the specified type
     * 
     * @param <T>
     * @param clazz
     * @return The {@link JsonReader} or {@code null} if no {@link JsonReader}
     *         is found
     */
    <T> JsonReader<T> get(Class<T> clazz);
}
