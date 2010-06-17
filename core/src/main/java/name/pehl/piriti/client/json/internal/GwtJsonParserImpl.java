package name.pehl.piriti.client.json.internal;

import name.pehl.piriti.client.json.JsonParser;

import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

/**
 * {@link JsonParser} implementation using {@link JSONParser}.
 * 
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class GwtJsonParserImpl implements JsonParser
{
    @Override
    public JSONValue parse(String text) throws JSONException
    {
        return JSONParser.parse(text);
    }
}
