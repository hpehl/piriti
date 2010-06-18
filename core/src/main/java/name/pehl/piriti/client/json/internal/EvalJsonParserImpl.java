package name.pehl.piriti.client.json.internal;

import name.pehl.piriti.client.json.JsonParser;

import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

/**
 * {@link JsonParser} implementation using {@link JSONParser} which uses
 * Javascript eval().
 * <p>
 * <b>Please note:</b><br/>
 * The JavaScript eval() function can execute arbitrary script. DO NOT pass an
 * untrusted string into this method.
 * 
 * @author $Author$
 * @version $Date$ $Revision: 531
 *          $
 */
public class EvalJsonParserImpl implements JsonParser
{
    @Override
    public JSONObject parse(String text) throws JSONException
    {
        JSONValue value = JSONParser.parse(text);
        return value != null ? value.isObject() : null;
    }
}
