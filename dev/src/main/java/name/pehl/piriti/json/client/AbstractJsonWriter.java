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
        boolean ok = false;
        try
        {
            ok = Byte.valueOf(numberValue) != null;
        }
        catch (NumberFormatException e)
        {
            ok = false;
        }
        if (!ok)
        {
            try
            {
                ok = Double.valueOf(numberValue) != null;
            }
            catch (NumberFormatException e)
            {
                ok = false;
            }
        }
        if (!ok)
        {
            try
            {
                ok = Float.valueOf(numberValue) != null;
            }
            catch (NumberFormatException e)
            {
                ok = false;
            }
        }
        if (!ok)
        {
            try
            {
                ok = Integer.valueOf(numberValue) != null;
            }
            catch (NumberFormatException e)
            {
                ok = false;
            }
        }
        if (!ok)
        {
            try
            {
                ok = Long.valueOf(numberValue) != null;
            }
            catch (NumberFormatException e)
            {
                ok = false;
            }
        }
        if (!ok)
        {
            try
            {
                ok = Short.valueOf(numberValue) != null;
            }
            catch (NumberFormatException e)
            {
                ok = false;
            }
        }
        return ok;
    }
}
