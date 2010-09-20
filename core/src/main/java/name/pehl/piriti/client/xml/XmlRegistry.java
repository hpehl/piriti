package name.pehl.piriti.client.xml;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Singleton;

/**
 * A registry for {@linkplain XmlReader}s and {@linkplain XmlWriter}s. All
 * readers and writers are registered against this registry. Thereby references
 * between POJOs can be mapped.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 8 $
 */
@Singleton
public final class XmlRegistry
{
    private final Map<Class<?>, XmlReader<?>> readers;
    private final Map<Class<?>, XmlWriter<?>> writers;


    public XmlRegistry()
    {
        readers = new HashMap<Class<?>, XmlReader<?>>();
        writers = new HashMap<Class<?>, XmlWriter<?>>();
    }


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
    public <T> void register(Class<T> clazz, XmlReader<T> reader)
    {
        readers.put(clazz, reader);
    }


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
    public <T> void register(Class<T> clazz, XmlWriter<T> writer)
    {
        writers.put(clazz, writer);
    }


    /**
     * Returns the {@link XmlReader} for the specified type
     * 
     * @param <T>
     * @param clazz
     * @return The {@link XmlReader} or {@code null} if no {@link XmlReader} is
     *         found
     */
    @SuppressWarnings("unchecked")
    public <T> XmlReader<T> getReader(Class<T> clazz)
    {
        return (XmlReader<T>) readers.get(clazz);
    }


    /**
     * Returns the {@link XmlWriter} for the specified type
     * 
     * @param <T>
     * @param clazz
     * @return The {@link XmlWriter} or {@code null} if no {@link XmlWriter} is
     *         found
     */
    @SuppressWarnings("unchecked")
    public <T> XmlWriter<T> getWriter(Class<T> clazz)
    {
        return (XmlWriter<T>) writers.get(clazz);
    }
}
