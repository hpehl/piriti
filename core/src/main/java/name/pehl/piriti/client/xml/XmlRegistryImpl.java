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
    private Map<Class<?>, XmlReader<?>> registry;


    public XmlRegistryImpl()
    {
        registry = new HashMap<Class<?>, XmlReader<?>>();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void register(Class<T> clazz, XmlReader<T> reader)
    {
        registry.put(clazz, reader);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> XmlReader<T> get(Class<T> clazz)
    {
        if (clazz != null)
        {
            return (XmlReader<T>) registry.get(clazz);
        }
        return null;
    }
}
