package name.pehl.piriti.client.json;

import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONObject;
import com.google.inject.internal.Nullable;

/**
 * Interface for parsing JSON.
 * 
 * @author $Author$
 * @version $Date$ $Revision: 531
 *          $
 */
public interface JsonParser
{
    /**
     * Parse the specified string and return the relevant JSON object.
     * 
     * @param text
     * @return
     */
    public JSONObject parse(@Nullable String text) throws JSONException;
}
