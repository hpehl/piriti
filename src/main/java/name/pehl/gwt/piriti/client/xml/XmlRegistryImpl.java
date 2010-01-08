package name.pehl.gwt.piriti.client.xml;

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation for the {@link XmlReader}
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class XmlRegistryImpl implements XmlRegistry
{
    private Map<Class<?>, XmlReader<?>> registry;


    public XmlRegistryImpl()
    {
        registry = new HashMap<Class<?>, XmlReader<?>>();
    }


    /**
     * Both the clazz and the reader must not be null.
     */
    @Override
    public <T> void register(Class<T> clazz, XmlReader<T> reader)
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
    public <T> XmlReader<T> get(Class<T> clazz)
    {
        if (clazz != null)
        {
            return (XmlReader<T>) registry.get(clazz);
        }
        return null;
    }
}
