package name.pehl.piriti.restlet.client.json;

import java.io.IOException;
import java.util.List;

import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.restlet.client.PiritiRepresentation;
import name.pehl.totoe.json.client.JSONObject;
import name.pehl.totoe.json.client.JsonParser;

import org.restlet.client.data.MediaType;
import org.restlet.client.ext.json.JsonRepresentation;
import org.restlet.client.representation.Representation;

import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONValue;

/**
 * Representation which uses an {@link JsonReader} for converting JSON to an
 * instance of T.
 * 
 * @param <T>
 *            The model type
 * @author $Author$
 * @version $Date$ $Revision: 264
 *          $
 */
public class PiritiJsonRepresentation<T> extends JsonRepresentation implements PiritiRepresentation<T>
{
    // -------------------------------------------------------- private members

    /** The JsonReader for converting the JSON to an instance of T. */
    private final JsonReader<T> jsonReader;

    /** The wrapped JSON value. */
    private JSONValue value;

    /** The source JSON representation. */
    private Representation jsonRepresentation;


    // ----------------------------------------------------------- constructors

    /**
     * Constructor for an null JSON value.
     * 
     * @param jsonReader
     *            theJsonReader for converting the JSON to an instance of T.
     * @param mediaType
     *            The representation's media type.
     */
    public PiritiJsonRepresentation(JsonReader<T> jsonReader, MediaType mediaType)
    {
        super(mediaType);
        this.jsonReader = jsonReader;
    }


    /**
     * Constructor from an existing JSON value.
     * 
     * @param jsonReader
     *            theJsonReader for converting the JSON to an instance of T.
     * @param mediaType
     *            The representation's media type.
     * @param value
     *            The source JSON value.
     */
    public PiritiJsonRepresentation(JsonReader<T> jsonReader, MediaType mediaType, JSONValue value)
    {
        super(mediaType, value);
        this.value = value;
        this.jsonReader = jsonReader;
    }


    /**
     * Constructor.
     * 
     * @param jsonReader
     *            theJsonReader for converting the JSON to an instance of T.
     * @param jsonRepresentation
     *            A source JSON representation to parse.
     */
    public PiritiJsonRepresentation(JsonReader<T> jsonReader, Representation jsonRepresentation)
    {
        super(jsonRepresentation);
        this.jsonReader = jsonReader;
    }


    /**
     * Constructor from a JSON string.
     * 
     * @param jsonReader
     *            theJsonReader for converting the JSON to an instance of T.
     * @param jsonString
     *            The JSON string.
     */
    public PiritiJsonRepresentation(JsonReader<T> jsonReader, String jsonString)
    {
        super(jsonString);
        this.jsonReader = jsonReader;
    }


    // --------------------------------------------------------- public methods

    /**
     * @return the JsonReader for converting the JSON to an instance of T.
     */
    public JsonReader<T> getJsonReader()
    {
        return jsonReader;
    }


    /**
     * Converts the JSON to an instance of T using the {@link JsonReader} given
     * as constructor argument. Returns null if {@link #getJsonObject()} or
     * {@link JsonReader} is null.
     * 
     * @return the converted instance of T or null if {@link #getJsonObject()}
     *         or {@link JsonReader} is null.
     * @throws IOException
     */
    @Override
    public T getModel() throws IOException
    {
        T model = null;
        JSONObject jsonObject = getJsonObject();
        if (jsonObject != null)
        {
            model = jsonReader.read(jsonObject);
        }
        return model;
    }


    /**
     * Converts the JSON to a list of Ts using the {@link JsonReader} given as
     * constructor argument. Returns null if {@link #getJsonObject()} or
     * {@link JsonReader} is null. More precisely the JSON has to be a JSON
     * object with one key (name does not matter). The value of this key has to
     * be a JSON array which is converted to instances of T.
     * 
     * @return the list of converted Ts or null if {@link #getJsonObject()} or
     *         {@link JsonReader} is null.
     * @throws IOException
     */
    @Override
    public List<T> getModels() throws IOException
    {
        List<T> models = null;
        JSONObject jsonObject = getJsonObject();
        if (jsonObject != null)
        {
            models = jsonReader.readList(jsonObject);
        }
        return models;
    }


    /**
     * {@inheritDoc}
     * 
     * @return The converted JSON object.
     * @throws IOException
     */
    public JSONObject getJsonObject() throws IOException
    {
        if (getValue() != null)
        {
            return (JSONObject) getValue().isObject();
        }
        return null;
    }


    /**
     * {@inheritDoc}
     * 
     * @return The wrapped DOM document.
     * @throws IOException
     */
    @Override
    public JSONValue getValue() throws IOException
    {
        if (value == null)
        {
            if (jsonRepresentation != null)
            {
                value = new JsonParser().parse(jsonRepresentation.getText());
            }
            else
            {
                this.value = JSONNull.getInstance();
            }
        }
        return this.value;
    }
}
