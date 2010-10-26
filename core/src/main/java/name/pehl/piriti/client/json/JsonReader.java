package name.pehl.piriti.client.json;

import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONObject;
import com.google.inject.internal.Nullable;

/**
 * Interface for converting JSON data to an instance of T or a list of Ts. The
 * implementation for this interface is generated using deferred binding. All
 * properties of T which are annotated with {@link Json} are handled by the
 * generated JsonReader implementation. If the path expression behind
 * {@link Json} returns some data != null the relevant property is assigned with
 * the converted value, otherwise the property remains unchanged.
 * <p>
 * The setup of the JsonReader is inspired by the UiBinder and is typically
 * specified as an inner class:
 * 
 * <pre>
 * pubilc ModalGirlfriendMediator
 * {
 *     interface Reader extends JsonReader&lt;ModalGirlfriendMediator&gt; {}
 *     public static final Reader JSON_READER = GWT.create(Reader.class);
 *     
 *     // The properties of this POJO annotated with {@code @}Json.
 * }
 * </pre>
 * 
 * JSON data can then be converted to an instance of ModalGirlfriendMediator by
 * calling
 * 
 * <pre>
 * String json = &quot;...&quot;;
 * ModalGirlfriendMediator mgm = ModalGirlfriendMediator.JSON_READER.read(jsonString);
 * </pre>
 * 
 * @param <T>
 *            The type
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 46 $
 */
public interface JsonReader<T>
{
    // -------------------------------------------------------------- read list

    /**
     * Convert the specified JSON string to a list of Ts according to the
     * annotated properties in T. The JSON string must be a valid JSON object
     * with <i>one</i> key / value pair. The value must be a JSON array which is
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
     * <td>empty JSON<br/>
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
     * <td>empty list</td>
     * </tr>
     * <tr>
     * <td>JSON with one key and a non-empty array<br/>
     * {"foo": [...]}</td>
     * <td>a list of Ts according to the annotated properties in T</td>
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
     * annotated properties in T. The JSON string must be a valid JSON object
     * with key / value pairs. The array is taken from the specified key and is
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
     * <td>any value</td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>empty / blank string</td>
     * <td>any value</td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>empty JSON<br/>
     * {}</td>
     * <td>any value</td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>JSON with a non-existing key<br/>
     * {"foo": null}<br/>
     * {"foo": 123}<br/>
     * {"foo": [...]}</td>
     * <td>"bar"</td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>JSON with the specified key and a value which is null or no array<br/>
     * {"foo": null}<br/>
     * {"foo": 123}</td>
     * <td>"foo"</td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>JSON with the specified key and a avlue which is an empty array<br/>
     * {"foo": []}</td>
     * <td>"foo"</td>
     * <td>empty list</td>
     * </tr>
     * <tr>
     * <td>JSON with the specified key and a non-empty array<br/>
     * {"foo": [...]}</td>
     * <td>"foo"</td>
     * <td>a list of Ts according to the annotated properties in T</td>
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
     * annotated properties in T. The JSON object must contain <i>one</i> key /
     * value pair. The value must be JSON array which converted to the list of
     * Ts.
     * <p>
     * Depending on {@code jsonObject} the following value is returned by this
     * method:
     * <table border="1" cellpadding="2" cellspacing="2">
     * <tr>
     * <th>jsonObject</th>
     * <th>Result</th>
     * </tr>
     * <tr>
     * <td><code>null</code></td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>empty JSON<br/>
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
     * <td>empty list</td>
     * </tr>
     * <tr>
     * <td>JSON with one key and a non-empty array<br/>
     * {"foo": [...]}</td>
     * <td>a list of Ts according to the annotated properties in T</td>
     * </tr>
     * </table>
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
     * annotated properties in T. The JSON object must contains the specified
     * array. This array is converted to the list of Ts.
     * <p>
     * Depending on {@code jsonObject} and {@code arrayKey} the following value
     * is returned by this method:
     * <table border="1" cellpadding="2" cellspacing="2">
     * <tr>
     * <th>jsonObject</th>
     * <th>arrayKey</th>
     * <th>Result</th>
     * </tr>
     * <tr>
     * <td><code>null</code></td>
     * <td>any value</td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>empty JSON<br/>
     * {}</td>
     * <td>any value</td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>JSON with a non-existing key<br/>
     * {"foo": null}<br/>
     * {"foo": 123}<br/>
     * {"foo": [...]}</td>
     * <td>"bar"</td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>JSON with the specified key and a value which is null or no array<br/>
     * {"foo": null}<br/>
     * {"foo": 123}</td>
     * <td>"foo"</td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>JSON with the specified key and a avlue which is an empty array<br/>
     * {"foo": []}</td>
     * <td>"foo"</td>
     * <td>empty list</td>
     * </tr>
     * <tr>
     * <td>JSON with the specified key and a non-empty array<br/>
     * {"foo": [...]}</td>
     * <td>"foo"</td>
     * <td>a list of Ts according to the annotated properties in T</td>
     * </tr>
     * </table>
     * 
     * @param jsonObject
     *            The JSON object used as input. May be <code>null</code>.
     * @param arrayKey
     *            The key containing the JSON array.
     * @return A list of T instances with the mapped JSON data, an empty list or
     *         {@code null} (see above).
     * @throws JSONException
     *             if the specified string represents no valid JSON data
     */
    List<T> readList(@Nullable JSONObject jsonObject, String arrayKey) throws JSONException;


    /**
     * Convert the specified JSON array to a list of Ts according to the
     * annotated properties in T.
     * <p>
     * Depending on {@code jsonArray} the following value is returned by this
     * method:
     * <table border="1" cellpadding="2" cellspacing="2">
     * <tr>
     * <th>jsonArray</th>
     * <th>Result</th>
     * </tr>
     * <tr>
     * <td><code>null</code></td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>empty array<br/>
     * []</td>
     * <td><code>empty list</code></td>
     * </tr>
     * <tr>
     * <td>Non-empty array<br/>
     * {[...]}</td>
     * <td>a list of Ts according to the annotated properties in T</td>
     * </tr>
     * </table>
     * 
     * @param jsonArray
     *            The JSON array used as input. May be <code>null</code>.
     * @return A list of T instances with the mapped JSON data, an empty list or
     *         {@code null} (see above).
     * @throws JSONException
     *             if the specified string represents no valid JSON data
     */
    List<T> readList(@Nullable JSONArray jsonArray) throws JSONException;


    // ------------------------------------------------------------ read single

    /**
     * Convert the specified JSON string to an instance of T according to the
     * annotated properties in T. The JSON string must be a valid JSON object
     * with key / value pairs.
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
     * <td>empty JSON<br/>
     * {}</td>
     * <td>new instance of T</td>
     * </tr>
     * <tr>
     * <td>JSON with keys / values<br/>
     * {"key": "value", ...}</td>
     * <td>an instance of T with the mapped JSON data</td>
     * </tr>
     * </table>
     * 
     * @param jsonString
     *            The JSON string used as input. May be <code>null</code>.
     * @return An instance of T with the mapped JSON data or {@code null} (see
     *         above).
     * @throws JSONException
     *             if the specified string represents no valid JSON data
     */
    T read(@Nullable String jsonString) throws JSONException;


    /**
     * Convert the specified JSON object to an instance of T according to the
     * annotated properties in T.
     * <p>
     * Depending on {@code jsonObject} the following value is returned by this
     * method:
     * <table border="1" cellpadding="2" cellspacing="2">
     * <tr>
     * <th>jsonObject</th>
     * <th>Result</th>
     * </tr>
     * <tr>
     * <td><code>null</code></td>
     * <td><code>null</code></td>
     * </tr>
     * <tr>
     * <td>empty JSON<br/>
     * {}</td>
     * <td>new instance of T</td>
     * </tr>
     * <tr>
     * <td>JSON with keys / values<br/>
     * {"key": "value", ...}</td>
     * <td>an instance of T with the mapped JSON data</td>
     * </tr>
     * </table>
     * 
     * @param jsonObject
     *            The JSON object used as input. May be <code>null</code>.
     * @return An instance of T with the mapped JSON data or {@code null} (see
     *         above).
     * @throws JSONException
     *             if the specified string represents no valid JSON data
     */
    T read(@Nullable JSONObject jsonObject) throws JSONException;
}
