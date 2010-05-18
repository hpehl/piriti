package name.pehl.piriti.sample.server.rest;

import java.io.IOException;
import java.io.InputStream;

import org.restlet.data.MediaType;
import org.restlet.representation.InputRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-03-25 15:16:49 +0100 (Do, 25 Mrz 2010) $ $Revision: 272
 *          $
 */
public class PlaygroundResource extends ServerResource
{
    @Get("xml")
    public Representation represent() throws IOException
    {
        return loadResource("name/pehl/piriti/sample/server/rest/playground.xml", MediaType.TEXT_XML);
    }


    private Representation loadResource(String resource, MediaType mediaType)
    {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = contextClassLoader.getResourceAsStream(resource);
        InputRepresentation representation = new InputRepresentation(stream, mediaType);
        return representation;
    }
}
