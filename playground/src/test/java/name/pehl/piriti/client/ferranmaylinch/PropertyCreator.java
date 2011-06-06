package name.pehl.piriti.client.ferranmaylinch;

import java.util.Date;

import name.pehl.piriti.json.client.JsonInstanceCreator;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

public class PropertyCreator extends JsonInstanceCreator<Property>
{
    @Override
    public Property newInstance(JSONValue context)
    {
        JSONObject object = context.isObject();

        String name = object.get("name").isString().stringValue();
        String type = object.get("type").isString().stringValue();
        JSONValue jsonValue = object.get("value");

        if (type.equals("string"))
        {
            String value = jsonValue.isString().stringValue();
            return new PropertyString(name, value);
        }
        else if (type.equals("integer"))
        {
            double value = jsonValue.isNumber().doubleValue();
            return new PropertyInteger(name, (int) value);
        }
        else if (type.equals("date"))
        {
            String value = jsonValue.isString().stringValue();
            Date date = DateTimeFormat.getFormat(Constants.DATE_FORMAT).parse(value);
            return new PropertyDate(name, date);
        }
        return null;
    }
}
