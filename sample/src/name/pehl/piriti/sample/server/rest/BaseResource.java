package name.pehl.piriti.sample.server.rest;

import java.io.InputStream;

import org.restlet.data.MediaType;
import org.restlet.representation.InputRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public abstract class BaseResource extends ServerResource
{
    protected Representation loadResource(String resource, MediaType mediaType)
    {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = contextClassLoader.getResourceAsStream(resource);
        InputRepresentation representation = new InputRepresentation(stream, mediaType);
        return representation;
    }
}
