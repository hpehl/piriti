package name.pehl.piriti.json.client;

import static java.util.logging.Level.FINE;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import name.pehl.piriti.commons.client.AbstractReader;
import name.pehl.piriti.commons.client.InstanceContextHolder;
import name.pehl.piriti.commons.client.ModelReadEvent;
import name.pehl.piriti.converter.client.Converter;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

/**
 * Base class for generated JsonReaders. Contains common code and methods.
 * 
 * @param <T>
 *            The type
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public abstract class AbstractJsonReader<T> extends AbstractReader<T, JSONObject> implements JsonReader<T>
{
    // ----------------------------------------------------------------- fields

    protected final JsonRegistry jsonRegistry;


    // ----------------------------------------------------------- constructors

    protected AbstractJsonReader()
    {
        super();
        this.jsonRegistry = JsonGinjector.INJECTOR.getJsonRegistry();
    }


    // ------------------------------------------------------ read list methods

    @Override
    public List<T> readList(String jsonString)
    {
        List<T> models = null;
        if (jsonString != null && jsonString.trim().length() != 0)
        {
            JSONObject jsonObject = JSONParser.parseStrict(jsonString).isObject();
            if (jsonObject != null)
            {
                Set<String> keys = jsonObject.keySet();
                if (keys != null && !keys.isEmpty())
                {
                    String arrayKey = keys.iterator().next();
                    JSONValue jsonValue = jsonObject.get(arrayKey);
                    if (jsonValue != null)
                    {
                        JSONArray jsonArray = jsonValue.isArray();
                        if (jsonArray != null)
                        {
                            models = readList(jsonArray);
                        }
                    }
                }
            }
            else
            {
                throw new JSONException("\"" + jsonString + "\" represents invalid json data!");
            }
        }
        return models;
    }


    @Override
    public List<T> readList(String jsonString, String arrayKey)
    {
        List<T> models = null;
        if (jsonString != null && jsonString.trim().length() != 0)
        {
            JSONObject jsonObject = JSONParser.parseStrict(jsonString).isObject();
            if (jsonObject != null)
            {
                if (arrayKey != null)
                {
                    JSONValue jsonValue = jsonObject.get(arrayKey);
                    if (jsonValue != null)
                    {
                        JSONArray jsonArray = jsonValue.isArray();
                        if (jsonArray != null)
                        {
                            models = readList(jsonArray);
                        }
                    }
                }
            }
            else
            {
                throw new JSONException("\"" + jsonString + "\" represents invalid json data!");
            }
        }
        return models;
    }


    @Override
    public List<T> readList(JSONObject jsonObject)
    {
        List<T> models = null;
        if (jsonObject != null)
        {
            Set<String> keys = jsonObject.keySet();
            if (keys != null && !keys.isEmpty())
            {
                String arrayKey = keys.iterator().next();
                JSONValue jsonValue = jsonObject.get(arrayKey);
                if (jsonValue != null)
                {
                    JSONArray jsonArray = jsonValue.isArray();
                    if (jsonArray != null)
                    {
                        models = readList(jsonArray);
                    }
                }
            }
        }
        return models;
    }


    @Override
    public List<T> readList(JSONObject jsonObject, String arrayKey)
    {
        List<T> models = null;
        if (jsonObject != null && arrayKey != null)
        {
            JSONValue jsonValue = jsonObject.get(arrayKey);
            if (jsonValue != null)
            {
                JSONArray jsonArray = jsonValue.isArray();
                if (jsonArray != null)
                {
                    models = readList(jsonArray);
                }
            }
        }
        return models;
    }


    @Override
    public List<T> readList(JSONArray jsonArray)
    {
        List<T> models = null;
        List<InstanceContextHolder<T, JSONObject>> instanceContextHolders;

        if (jsonArray != null)
        {
            models = new ArrayList<T>();
            instanceContextHolders = new ArrayList<InstanceContextHolder<T, JSONObject>>();

            if (logger.isLoggable(FINE))
            {
                logger.log(FINE, "First iteration over JSON array to create models and process IDs");
            }
            int size = jsonArray.size();
            for (int i = 0; i < size; i++)
            {
                JSONValue currentJsonValue = jsonArray.get(i);
                if (currentJsonValue != null)
                {
                    JSONObject currentJsonObject = currentJsonValue.isObject();
                    if (currentJsonObject != null)
                    {
                        T model = readId(currentJsonObject);
                        if (model != null)
                        {
                            models.add(model);
                            instanceContextHolders.add(new InstanceContextHolder<T, JSONObject>(model,
                                    currentJsonObject));
                        }
                    }
                }
            }

            if (logger.isLoggable(FINE))
            {
                logger.log(FINE, "Second iteration over generated models to map properties and IDREFs");
            }
            for (InstanceContextHolder<T, JSONObject> ich : instanceContextHolders)
            {
                T model = ich.getInstance();
                readProperties(ich.getContext(), model);
                readIdRefs(ich.getContext(), model);
                ModelReadEvent.fire(this, model, ich.getContext());
            }
        }
        return models;
    }


    // ---------------------------------------------------- read single methods

    @Override
    public T read(String jsonString)
    {
        T model = null;
        if (jsonString != null && jsonString.trim().length() != 0)
        {
            JSONObject jsonObject = JSONParser.parseStrict(jsonString).isObject();
            if (jsonObject != null)
            {
                model = internalRead(jsonObject);
            }
            else
            {
                throw new JSONException("\"" + jsonString + "\" represents invalid json data!");
            }
        }
        return model;
    }


    @Override
    public T read(JSONObject jsonObject)
    {
        T model = null;
        if (jsonObject != null)
        {
            model = internalRead(jsonObject);
        }
        return model;
    }


    protected T internalRead(JSONObject jsonObject)
    {
        if (jsonObject == null)
        {
            return null;
        }
        T model = readId(jsonObject);
        readProperties(jsonObject, model);
        readIdRefs(jsonObject, model);
        ModelReadEvent.fire(this, model, jsonObject);
        return model;
    }


    // ------------------------------------------------ read values and objects

    protected <V> V readValue(JSONValue jsonValue, Converter<V> converter)
    {
        V result = null;
        if (jsonValue != null && converter != null)
        {
            if (jsonValue.isNull() == null)
            {
                String stringValue;
                JSONString jsonString = jsonValue.isString();
                if (jsonString != null)
                {
                    stringValue = jsonString.stringValue();
                }
                else
                {
                    stringValue = jsonValue.toString();
                }
                if (stringValue != null)
                {
                    result = converter.convert(stringValue);
                }
            }
        }
        return result;
    }


    protected <O> O readObject(JSONValue jsonValue, JsonReader<O> jsonReader)
    {
        O result = null;
        if (jsonValue != null && jsonReader != null)
        {
            JSONObject jsonObject = jsonValue.isObject();
            if (jsonObject != null)
            {
                result = jsonReader.read(jsonObject);
            }
        }
        return result;
    }
}
