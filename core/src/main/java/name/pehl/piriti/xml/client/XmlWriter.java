package name.pehl.piriti.xml.client;

import java.util.List;

/**
 * Interface for serializing an instance of T or a list of Ts to an XML
 * document. Not yet implemented!
 * 
 * @param <T>
 *            The type
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 46 $
 */
public interface XmlWriter<T>
{
    String toXml(List<T> models, String rootElement);


    String toXml(T model);
}
