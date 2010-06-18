package name.pehl.piriti.sample.server.rest;

import java.io.IOException;

import org.json.JSONException;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-05-18 23:30:44 +0200 (Di, 18 Mai 2010) $ $Revision: 272
 *          $
 */
public class BooksResource extends BaseResource
{
    @Get("json")
    public Representation listAsJson() throws JSONException
    {
        return loadResource("name/pehl/piriti/sample/server/rest/books.json", MediaType.APPLICATION_JSON);
    }


    @Get("xml")
    public Representation listAsXml() throws IOException
    {
        return loadResource("name/pehl/piriti/sample/server/rest/books.xml", MediaType.TEXT_XML);
    }
}
