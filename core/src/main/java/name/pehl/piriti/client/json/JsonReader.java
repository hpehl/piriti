package name.pehl.piriti.client.json;

import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

/**
 * @param <T>
 *            The type
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public interface JsonReader<T>
{
    T read(String jsonString);


    T read(JSONObject jsonObject);


    List<T> readList(String jsonString);


    List<T> readList(JSONArray jsonArray);
}
