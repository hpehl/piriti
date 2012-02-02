package name.pehl.piriti.client.createwith;

import name.pehl.piriti.json.client.JsonInstanceCreator;

import com.google.gwt.json.client.JSONValue;

public class CustomInstanceCreator extends JsonInstanceCreator<SaveFileResult>
{
    @Override
    public SaveFileResult newInstance(JSONValue context)
    {
        return new SaveFileResult();
    }
}
