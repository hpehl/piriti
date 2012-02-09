package name.pehl.piriti.client.polymorph;

import name.pehl.piriti.json.client.JsonInstanceCreator;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class JsonMediumCreator extends JsonInstanceCreator<Medium>
{
    @Override
    public Medium newInstance(JSONValue context)
    {
        Medium medium = null;
        JSONObject jsonObject = context.isObject();
        if (jsonObject != null)
        {
            JSONValue idValue = jsonObject.get("id");
            if (idValue != null)
            {
                JSONString idString = idValue.isString();
                if (idString != null)
                {
                    String id = idString.stringValue();
                    if (id.startsWith("isbn-"))
                    {
                        medium = new Book();
                    }
                    else if (id.startsWith("cd-"))
                    {
                        medium = new Cd();
                    }
                    if (id.startsWith("dvd-"))
                    {
                        medium = new Dvd();
                    }
                }
            }
        }
        return medium;
    }
}
