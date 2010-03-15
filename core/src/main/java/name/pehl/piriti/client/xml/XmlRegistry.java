package name.pehl.piriti.client.xml;

/**
 * A registry for {@linkplain XmlReader}s. All {@linkplain XmlReader}s are
 * registered against this registry. This way nested types can be mapped.
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
     * Returns the {@link XmlReader} for the specified type
     * 
     * @param <T>
     * @param clazz
     * @return The {@link XmlReader} or {@code null} if no {@link XmlReader} is
     *         found
     */
    <T> XmlReader<T> get(Class<T> clazz);
}
