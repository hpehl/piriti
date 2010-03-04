package name.pehl.gwt.piriti.client.json;

import java.util.List;

/**
 * @param <T>
 *            The type
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public interface JsonReader<T>
{
    T readSingle(String jsonString);


    List<T> readList(String jsonString);
}
