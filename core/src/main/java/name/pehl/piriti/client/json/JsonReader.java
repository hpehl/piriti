package name.pehl.piriti.client.json;

import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONObject;
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
     * Convert the specified JSON string to a list of Ts according to the
     * annotated fields in T. The JSON string must be a valid JSON object with
     * <i>one</i> key / value pair. The value must be a JSON array which is
     * converted to the list of Ts.
     * <p>
     * Depending on {@code jsonString} the following value is returned by this
     * method:
     * <table border="1" cellpadding="2" cellspacing="2">
     * <tr>
     * <th>jsonString</th>
     * <th>Result</th>
     * </tr>
     * <tr>
     * <td><code>null</code></td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>empty / blank string</td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>empty JSON object<br/>
     * {}</td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>JSON with one key / value, which is null or no array<br/>
     * {"foo": null}<br/>
     * {"foo": 123}</td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>JSON with one key / value, which is an empty array<br/>
     * {"foo": []}</td>
     * <td>empty array</td>
     * </tr>
     * <tr>
     * <td>JSON with one key and a non-empty array<br/>
     * {"foo": [...]}</td>
     * <td>a list of Ts according to the annotated fields in T</td>
     * </tr>
     * </table>
     * 
     * @param jsonString
     *            The JSON string used as input. May be <code>null</code>.
     * @return A list of Ts with the mapped JSON data, an empty list or
     *         {@code null} (see above).
     * @throws JSONException
     *             if the specified string represents no valid JSON data
     */
    List<T> readList(@Nullable String jsonString) throws JSONException;


    /**
     * Convert the specified JSON string to a list of Ts according to the
     * annotated fields in T. The JSON string must be a valid JSON object with
     * key / value pairs. The array is taken from the specified key and is
     * converted to the list of Ts.
     * <p>
     * Depending on {@code jsonString} and {@code arrayKey} the following value
     * is returned by this method:
     * <table border="1" cellpadding="2" cellspacing="2">
     * <tr>
     * <th>jsonString</th>
     * <th>arrayKey</th>
     * <th>Result</th>
     * </tr>
     * <tr>
     * <td><code>null</code></td>
     * <td><code>any value</code></td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>empty / blank string</td>
     * <td><code>any value</code></td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>empty JSON object<br/>
     * {}</td>
     * <td><code>any value</code></td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>JSON with a non-existing key<br/>
     * {"foo": null}<br/>
     * {"foo": 123}<br/>
     * {"foo": [...]}</td>
     * <td><code>"bar"</code></td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>JSON with the specified key and a value which is null or no array<br/>
     * {"foo": null}<br/>
     * {"foo": 123}</td>
     * <td><code>"foo"</code></td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>JSON with the specified key and a avlue which is an empty array<br/>
     * {"foo": []}</td>
     * <td><code>"foo"</code></td>
     * <td>empty array</td>
     * </tr>
     * <tr>
     * <td>JSON with the specified key and a non-empty array<br/>
     * {"foo": [...]}</td>
     * <td><code>"foo"</code></td>
     * <td>a list of Ts according to the annotated fields in T</td>
     * </tr>
     * </table>
     * 
     * @param jsonString
     *            The JSON string used as input. May be <code>null</code>.
     * @param arrayKey
     *            The key containing the JSON array.
     * @return A list of T instances with the mapped JSON data, an empty list or
     *         {@code null} (see above).
     * @throws JSONException
     *             if the specified string represents no valid JSON data
     */
    List<T> readList(@Nullable String jsonString, String arrayKey) throws JSONException;


    /**
     * Convert the specified JSON object to a list of Ts according to the
     * annotated fields in T. The JSON object must contain <i>one</i> key/value
     * pair. The value must be JSON array which converted to the list of Ts.
     * 
     * @param jsonObject
     *            The JSON object used as input. May be <code>null</code>.
     * @return A list of T instances with the mapped JSON data, an empty list or
     *         {@code null} (see above).
     * @throws JSONException
     *             if the specified string represents no valid JSON data
     */
    List<T> readList(@Nullable JSONObject jsonObject) throws JSONException;


    /**
     * Convert the specified JSON object to a list of Ts according to the
     * annotated fields in T. The JSON object must contains the specified array.
     * This array is converted to the list of Ts.
     * 
     * @param jsonObject
     *            The JSON object used as input. May be <code>null</code>.
     * @return A list of T instances with the mapped JSON data or {@code null}
     *         if the JSON object was {@code null}.
     * @throws JSONException
     *             if the specified string represents no valid JSON data
     */
    List<T> readList(@Nullable JSONObject jsonObject, String arrayKey) throws JSONException;


    /**
     * Convert the specified JSON array to a list of Ts according to the
     * annotated fields in T.
     * 
     * @param jsonArray
     *            The JSON array used as input. May be <code>null</code>.
     * @return A list of T instances with the mapped JSON data or {@code null}
     *         if the JSON array was {@code null}.
     * @throws JSONException
     *             if the specified string represents no valid JSON data
     */
    List<T> readList(@Nullable JSONArray jsonArray) throws JSONException;


    /**
     * Convert the specified JSON string to an instance of T according to the
     * annotated fields in T. The JSON string must be a valid JSON object with
     * key/value pairs.
     * 
     * @param jsonString
     *            The JSON string used as input. May be <code>null</code>.
     * @return An instance of T with the mapped JSON data or {@code null} if the
     *         JSON string was {@code null}.
     * @throws JSONException
     *             if the specified string represents no valid JSON data
     */
    T read(@Nullable String jsonString) throws JSONException;


    /**
     * Convert the specified JSON object to an instance of T according to the
     * annotated fields in T.
     * 
     * @param jsonObject
     *            The JSON object used as input. May be <code>null</code>.
     * @return An instance of T with the mapped JSON data or {@code null} if the
     *         JSON object was {@code null}.
     * @throws JSONException
     *             if the specified string represents no valid JSON data
     */
    T read(@Nullable JSONObject jsonObject) throws JSONException;
}
