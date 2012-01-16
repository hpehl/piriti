package name.pehl.piriti.json.client;

import java.util.Iterator;
import java.util.List;

import name.pehl.piriti.commons.client.AbstractWriter;

/**
 * Base class for generated JsonWriters. Contains common code and methods.
 * 
 * @param <T>
 *            The type
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public abstract class AbstractJsonWriter<T> extends AbstractWriter<T> implements JsonWriter<T>
{
    // ----------------------------------------------------------------- fields

    protected final JsonRegistry jsonRegistry;


    // ----------------------------------------------------------- constructors

    protected AbstractJsonWriter()
    {
        super();
        this.jsonRegistry = JsonGinjector.INJECTOR.getJsonRegistry();
    }


    // ----------------------------------------------------- write list methods

    @Override
    public String toJson(List<T> models, String arrayKey)
    {
        String json = null;
        if (models != null && arrayKey != null)
        {
            StringBuilder out = new StringBuilder();
            out.append("{\"").append(arrayKey).append("\":[");
            for (Iterator<T> iter = models.iterator(); iter.hasNext();)
            {
                T model = iter.next();
                String modelJson = toJson(model);
                if (modelJson != null)
                {
                    out.append(modelJson);
                }
                if (iter.hasNext())
                {
                    out.append(",");
                }
            }
            out.append("]}");
            json = out.toString();
        }
        return json;
    }


    // --------------------------------------------------------- helper methods

    protected boolean isBoolean(String booleanValue)
    {
        return Boolean.valueOf(booleanValue) != null;
    }


    protected boolean isNumber(String numberValue)
    {
        return Byte.valueOf(numberValue) != null || Double.valueOf(numberValue) != null
                || Float.valueOf(numberValue) != null || Integer.valueOf(numberValue) != null
                || Long.valueOf(numberValue) != null || Short.valueOf(numberValue) != null;
    }
}
