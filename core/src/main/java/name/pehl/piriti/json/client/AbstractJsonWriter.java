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
}
