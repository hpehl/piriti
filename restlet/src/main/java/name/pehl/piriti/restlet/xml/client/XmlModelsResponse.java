package name.pehl.piriti.restlet.xml.client;

import java.io.IOException;
import java.util.List;

import name.pehl.piriti.xml.client.XmlReader;

import org.restlet.client.Request;
import org.restlet.client.Response;
import org.restlet.client.Uniform;

/**
 * Convinience callback class which can be used together with
 * {@link PiritiXmlRepresentation}:
 * 
 * <pre>
 * ClientResource clientResource = new ClientResource("/resource/with/xml/representation");
 * clientResource.setOnResponse(new XmlModelsResponse&lt;Book&gt;(Book.JSON)
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
 * @author $Author$
 * @version $Date$ $Revision$
 */
public abstract class XmlModelsResponse<T> implements Uniform
{
    /** The XmlReader for converting the XML to instances of T. */
    private final XmlReader<T> xmlReader;


    /**
     * Construct a new instance with the specified reader.
     * 
     * @param xmlReader
     */
    public XmlModelsResponse(final XmlReader<T> xmlReader)
    {
        this.xmlReader = xmlReader;
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
        PiritiXmlRepresentation<T> representation = new PiritiXmlRepresentation<T>(xmlReader, response.getEntity());
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
