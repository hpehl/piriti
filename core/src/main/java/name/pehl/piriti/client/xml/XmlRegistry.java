package name.pehl.piriti.client.xml;

/**
 * A registry for {@linkplain XmlReader}s and {@linkplain XmlWriter}s. All
 * readers and writers are registered against this registry. Thereby references
 * between POJOs can be mapped.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 8 $
 */
public interface XmlRegistry
{
    /**
     * Registers the {@link XmlReader} for the specified type
     * 
     * @param <T>
     *            The type
     * @param clazz
     *            The class
     * @param reader
     *            The {@link XmlReader}
     */
    <T> void register(Class<T> clazz, XmlReader<T> reader);


    /**
     * Registers the {@link XmlWriter} for the specified type
     * 
     * @param <T>
     *            The type
     * @param clazz
     *            The class
     * @param writer
     *            The {@link XmlWriter}
     */
    <T> void register(Class<T> clazz, XmlWriter<T> writer);


    /**
     * Returns the {@link XmlReader} for the specified type
     * 
     * @param <T>
     * @param clazz
     * @return The {@link XmlReader} or {@code null} if no {@link XmlReader} is
     *         found
     */
    <T> XmlReader<T> getReader(Class<T> clazz);


    /**
     * Returns the {@link XmlWriter} for the specified type
     * 
     * @param <T>
     * @param clazz
     * @return The {@link XmlWriter} or {@code null} if no {@link XmlWriter} is
     *         found
     */
    <T> XmlWriter<T> getWriter(Class<T> clazz);
}
