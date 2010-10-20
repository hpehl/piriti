package name.pehl.piriti.restlet.client.json;

import java.io.IOException;
import java.util.List;

import name.pehl.piriti.client.json.JsonReader;

import org.restlet.client.Request;
import org.restlet.client.Response;
import org.restlet.client.Uniform;

/**
 * Convinience callback class which can be used together with
 * {@link PiritiJsonRepresentation}:
 * 
 * <pre>
 * ClientResource clientResource = new ClientResource("/resource/with/json/representation");
 * clientResource.setOnResponse(new JsonModelsResponse&lt;Book&gt;(Book.JSON)
 * {
 *     {@code @}Override
 *     public void onSuccess(List&lt;Book&gt; books, Request request, Response response)
 *     {
 *         ...
 *     }
 *     
 *     {@code @}Override
 *     public void onError(IOException error, Request request, Response response)
 *     {
 *         ...
 *     }
 * });
 * </pre>
 * 
 * @param <T>
 *            The model type
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public abstract class JsonModelsResponse<T> implements Uniform
{
    /** The JsonReader for converting the JSON to instances of T. */
    private final JsonReader<T> jsonReader;


    /**
     * Construct a new instance with the specified reader.
     * 
     * @param jsonReader
     */
    public JsonModelsResponse(final JsonReader<T> jsonReader)
    {
        this.jsonReader = jsonReader;
    }


    /**
     * Reads and maps the models from the response and calls either
     * {@link #onSuccess(List, Request, Response)} or
     * {@link #onError(IOException, Request, Response)}.
     * 
     * @param request
     * @param response
     * @see org.restlet.client.Uniform#handle(org.restlet.client.Request,
     *      org.restlet.client.Response)
     */
    @Override
    public void handle(Request request, Response response)
    {
        PiritiJsonRepresentation<T> representation = new PiritiJsonRepresentation<T>(jsonReader, response.getEntity());
        try
        {
            List<T> models = representation.getModels();
            onSuccess(models, request, response);
        }
        catch (IOException e)
        {
            onError(e, request, response);
        }
    }


    /**
     * Called when the models were read and mapped successfully from the
     * response.
     * 
     * @param models
     * @param request
     * @param response
     */
    public abstract void onSuccess(List<T> models, Request request, Response response);


    /**
     * Called when there was an IO error when reading the models.
     * 
     * @param error
     * @param request
     * @param response
     */
    public abstract void onError(IOException error, Request request, Response response);
}
