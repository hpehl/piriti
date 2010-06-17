package name.pehl.piriti.client.gwttest;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author $Author$
 * @version $Date$ $Revision$
 */
public final class JsonFactoryHelper
{
    private JsonFactoryHelper()
    {
    }


    public static void appendKeyValue(StringBuilder json, String key, String value, boolean quote, boolean goon)
    {
        json.append("\"").append(key).append("\"").append(": ");
        if (quote)
        {
            json.append("\"");
        }
        json.append(value);
        if (quote)
        {
            json.append("\"");
        }
        if (goon)
        {
            json.append(", ");
        }
    }


    public static void appendKeyValueArray(StringBuilder json, String key, boolean quote, boolean goon,
            String... values)
    {
        json.append("\"").append(key).append("\"").append(": [");
        Iterator<String> iter = Arrays.asList(values).iterator();
        while (iter.hasNext())
        {
            String value = iter.next();
            appendValue(json, value, quote, iter.hasNext());
        }
        json.append("]");
        if (goon)
        {
            json.append(", ");
        }
    }


    public static void appendValue(StringBuilder json, String value, boolean quote, boolean goon)
    {
        if (quote)
        {
            json.append("\"");
        }
        json.append(value);
        if (quote)
        {
            json.append("\"");
        }
        if (goon)
        {
            json.append(", ");
        }
    }
}
