package name.pehl.piriti.sample.server.rest;

import java.io.IOException;

import org.json.JSONException;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;

/**
 * @author $Author$
 * @version $Date$ $Revision: 272
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
