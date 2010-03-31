package name.pehl.piriti.client.json;

import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.inject.internal.Nullable;

/**
 * Interface for converting JSON data to an instance of T or a list of Ts. The
 * implementation for this interface is generated using deferred binding. All
 * fields of T which are annotated with {@link JsonField} are handled by the
 * generated JsonReader implementation. If the path expression behind
 * {@link JsonField} returns some data != null the relevant field is assigned
 * with the converted value, otherwise the field remains unchanged.
 * <p>
 * Please note that the annotated fields in T must not be private!
 * <p>
 * The setup of the XmlReader is inspired by the UiBinder and is typically
 * specified as an inner class:
 * 
 * <pre>
 * pubilc ModalGirlfriendMediator
 * {
 *     interface Reader extends JsonReader&lt;ModalGirlfriendMediator&gt; {}
 *     public static final Reader JSON = GWT.create(Reader.class);
 *     
 *     // The fields of this POJO annotated with JsonField.
 * }
 * </pre>
 * 
 * JSON data can then be converted to an instance of ModalGirlfriendMediator by
 * calling
 * 
 * <pre>
 * String jsonString = ...;
 * ModalGirlfriendMediator mgm = ModalGirlfriendMediator.JSON.read(jsonString);
 * </pre>
 * 
 * @param <T>
 *            The type
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 46 $
 */
public interface JsonReader<T>
{
    /**
     * Convert the specified JSON string to an instance of T according to the
     * annotated fields in T. The JSON string must be a valid JSON object with
     * key/value pairs.
     * <p>
     * Please note that the JSON string is parsed using
     * {@link JSONParser#parse(String)} which in turn uses the JavaScript eval()
     * function. So please *do not* pass an untrusted string into this method.
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
     * annotated fields in T. The JSON string must be a valid JSON object with
     * <i>one</i> key/value pair. The value must be a JSON array which is
     * converted to the list of Ts.
     * <p>
     * Please note that the JSON string is parsed using
     * {@link JSONParser#parse(String)} which in turn uses the JavaScript eval()
     * function. So please *do not* pass an untrusted string into this method.
     * 
     * @param jsonString
     *            The JSON string used as input. May be <code>null</code>.
     * @return A list of T instances with the mapped JSON data or {@code null}
     *         if the JSON string was {@code null}.
     */
    List<T> readList(@Nullable String jsonString);


    /**
     * Convert the specified JSON string to a list of Ts according to the
     * annotated fields in T. The JSON string must be a valid JSON object with
     * key/value pairs. The array is taken from the specified key and is
     * converted to the list of Ts.
     * <p>
     * Please note that the JSON string is parsed using
     * {@link JSONParser#parse(String)} which in turn uses the JavaScript eval()
     * function. So please *do not* pass an untrusted string into this method.
     * 
     * @param jsonString
     *            The JSON string used as input. May be <code>null</code>.
     * @return A list of T instances with the mapped JSON data or {@code null}
     *         if the JSON string was {@code null}.
     */
    List<T> readList(@Nullable String jsonString, String arrayKey);


    /**
     * Convert the specified JSON object to a list of Ts according to the
     * annotated fields in T. The JSON object must contain <i>one</i> key/value
     * pair. The value must be JSON array which converted to the list of Ts.
     * 
     * @param jsonObject
     *            The JSON object used as input. May be <code>null</code>.
     * @return A list of T instances with the mapped JSON data or {@code null}
     *         if the JSON object was {@code null}.
     */
    List<T> readList(@Nullable JSONObject jsonObject);


    /**
     * Convert the specified JSON object to a list of Ts according to the
     * annotated fields in T. The JSON object must contains the specified array.
     * This array is converted to the list of Ts.
     * 
     * @param jsonObject
     *            The JSON object used as input. May be <code>null</code>.
     * @return A list of T instances with the mapped JSON data or {@code null}
     *         if the JSON object was {@code null}.
     */
    List<T> readList(@Nullable JSONObject jsonObject, String arrayKey);


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
