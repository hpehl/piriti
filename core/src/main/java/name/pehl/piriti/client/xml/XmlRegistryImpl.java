package name.pehl.piriti.client.xml;

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation for the {@link XmlRegistry}
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 138 $
 */
public class XmlRegistryImpl implements XmlRegistry
{
    private Map<Class<?>, XmlReader<?>> readers;
    private Map<Class<?>, XmlWriter<?>> writers;


    public XmlRegistryImpl()
    {
        readers = new HashMap<Class<?>, XmlReader<?>>();
        writers = new HashMap<Class<?>, XmlWriter<?>>();
    }


    @Override
    public <T> void register(Class<T> clazz, XmlReader<T> reader)
    {
        readers.put(clazz, reader);
    }


    @Override
    public <T> void register(Class<T> clazz, XmlWriter<T> writer)
    {
        writers.put(clazz, writer);
    }

    
    @Override
    @SuppressWarnings("unchecked")
    public <T> XmlReader<T> getReader(Class<T> clazz)
    {
        return (XmlReader<T>) readers.get(clazz);
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T> XmlWriter<T> getWriter(Class<T> clazz)
    {
        return (XmlWriter<T>) writers.get(clazz);
    }
}
