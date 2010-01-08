package name.pehl.gwt.piriti.client.xml;

import java.util.HashMap;
import java.util.Map;

/**
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


    @Override
    public <T> void register(Class<T> clazz, XmlReader<T> reader)
    {
        if (clazz != null && reader != null)
        {
            registry.put(clazz, reader);
        }
    }


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
