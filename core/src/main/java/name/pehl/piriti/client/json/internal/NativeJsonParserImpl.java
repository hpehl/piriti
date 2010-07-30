package name.pehl.piriti.client.json.internal;

import name.pehl.piriti.client.json.JsonParser;

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONObject;

/**
 * {@link JsonParser} implementation using the native JSON parser. More
 * precisely this implementation relies on <a
 * href="http://www.json.org/js.html">http://www.json.org/js.html</a> which uses
 * the native JSON parser if available and a javascript emulation if no native
 * JSON parser was found.
 * 
 * @author $Author$
 * @version $Date$ $Revision: 531
 *          $
 */
public class NativeJsonParserImpl implements JsonParser
{
    @Override
    public JSONObject parse(String text) throws JSONException
    {
        try
        {
            return parseImpl(text);
        }
        catch (JavaScriptException e)
        {
            throw new JSONException(e);
        }
    }


    private native JSONObject parseImpl(String text)
    /*-{
        try
        {
            var v = $wnd.JSON.parse(text);
            var jsonObject = @com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)(v);
            return jsonObject;
        }
        catch (e)
        {
            throw new Error(e);
        }
    }-*/;
}
