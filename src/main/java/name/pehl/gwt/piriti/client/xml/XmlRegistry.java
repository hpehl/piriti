package name.pehl.gwt.piriti.client.xml;

/**
 * @author $Author:$
 * @version $Revision:$
 */
public interface XmlRegistry
{
    <T> void register(Class<T> clazz, XmlReader<T> reader);


    <T> XmlReader<T> get(Class<T> clazz);
}
