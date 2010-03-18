package name.pehl.piriti.gxt.client.json;

import java.util.List;

import com.extjs.gxt.ui.client.data.ModelData;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.inject.internal.Nullable;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public interface JsonModelReader<T extends ModelData>
{
    /**
     * Convert the specified JSON string to an instance of T according to the
     * annotated fields in T. The JSON string should represent a valid JSON
     * object with key/value pairs. Please note that the JSON string is parsed
     * using {@link JSONParser#parse(String)} which in turn uses the JavaScript
     * eval() function. So please *do not* pass an untrusted string into this
     * method.
     * 
     * @param jsonString
     *            The JSON string used as input. May be <code>null</code>.
     * @return An instance of T with the mapped JSON data or {@code null} if the
     *         JSON string was {@code null}.
     */
    T read(@Nullable String jsonString);


    /**
     * Convert the specified JSON object to an instance of T according to the
     * annotated fields in T.
     * 
     * @param jsonObject
     *            The JSON object used as input. May be <code>null</code>.
     * @return An instance of T with the mapped JSON data or {@code null} if the
     *         JSON object was {@code null}.
     */
    T read(@Nullable JSONObject jsonObject);


    /**
     * Convert the specified JSON string to a list of Ts according to the
     * annotated fields in T. The JSON string should represent a valid JSON
     * array which contains one or more JSON objects. Please note that the JSON
     * string is parsed using {@link JSONParser#parse(String)} which in turn
     * uses the JavaScript eval() function. So please *do not* pass an untrusted
     * string into this method.
     * 
     * @param jsonString
     *            The JSON string used as input. May be <code>null</code>.
     * @return A list of T instances with the mapped JSON data or {@code null}
     *         if the JSON string was {@code null}.
     */
    List<T> readList(@Nullable String jsonString);


    /**
     * Convert the specified JSON array to a list of Ts according to the
     * annotated fields in T.
     * 
     * @param jsonArray
     *            The JSON array used as input. May be <code>null</code>.
     * @return A list of T instances with the mapped JSON data or {@code null}
     *         if the JSON array was {@code null}.
     */
    List<T> readList(@Nullable JSONArray jsonArray);
}
