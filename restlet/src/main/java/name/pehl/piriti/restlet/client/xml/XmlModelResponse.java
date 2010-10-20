package name.pehl.piriti.restlet.client.xml;

import java.io.IOException;

import name.pehl.piriti.client.xml.XmlReader;

import org.restlet.client.Request;
import org.restlet.client.Response;
import org.restlet.client.Uniform;

/**
 * Convinience callback class which can be used together with
 * {@link PiritiXmlRepresentation}:
 * 
 * <pre>
 * ClientResource clientResource = new ClientResource("/resource/with/xml/representation");
 * clientResource.setOnResponse(new XmlModelResponse&lt;Book&gt;(Book.JSON)
 * {
 *     {@code @}Override
 *     public void onSuccess(Book book, Request request, Response response)
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
public abstract class XmlModelResponse<T> implements Uniform
{
    /** The XmlReader for converting the XML to an instance of T. */
    private final XmlReader<T> xmlReader;


    /**
     * Construct a new instance with the specified reader.
     * 
     * @param xmlReader
     */
    public XmlModelResponse(final XmlReader<T> xmlReader)
    {
        this.xmlReader = xmlReader;
    }


    /**
     * Reads and maps the model from the response and calls either
     * {@link #onSuccess(Object, Request, Response)} or
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
            T model = representation.getModel();
            onSuccess(model, request, response);
        }
        catch (IOException e)
        {
            onError(e, request, response);
        }
    }


    /**
     * Called when the model was read and mapped successfully from the response.
     * 
     * @param model
     * @param request
     * @param response
     */
    public abstract void onSuccess(T model, Request request, Response response);


    /**
     * Called when there was an IO error when reading the model.
     * 
     * @param error
     * @param request
     * @param response
     */
    public abstract void onError(IOException error, Request request, Response response);
}
