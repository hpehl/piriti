package name.pehl.piriti.client.json;

import java.util.List;

/**
 * @param <T>
 *            The type
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public interface JsonReader<T>
{
    T read(String json);


    List<T> readList(String json);
}
