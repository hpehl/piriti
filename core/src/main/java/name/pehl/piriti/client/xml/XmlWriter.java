package name.pehl.piriti.client.xml;

import java.util.List;

import com.google.inject.internal.Nullable;

/**
 * Interface for serializing an instance of T or a list of Ts to an XML
 * document.
 * 
 * @param <T>
 *            The type
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 46 $
 */
public interface XmlWriter<T>
{
    String toXml(@Nullable List<T> values, String rootElement);


    String toXml(@Nullable T value);
}
