package name.pehl.piriti.sample.server.rest;

import java.io.IOException;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;

/**
 * Resource to test arbitrary XML which is loaded from
 * <code>name/pehl/piriti/sample/server/rest/playground.xml</code>.
 * 
 * @author $Author: harald.pehl $
 * @version $Date: 2010-03-25 15:16:49 +0100 (Do, 25 Mrz 2010) $ $Revision: 272
 *          $
 */
public class PlaygroundResource extends BaseResource
{
    @Get("xml")
    public Representation represent() throws IOException
    {
        return loadResource("name/pehl/piriti/sample/server/rest/playground.xml", MediaType.TEXT_XML);
    }
}
