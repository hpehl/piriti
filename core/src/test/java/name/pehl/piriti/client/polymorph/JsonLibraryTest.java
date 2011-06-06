package name.pehl.piriti.client.polymorph;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 131 $
 */
public class JsonLibraryTest extends AbstractLibraryTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String json = LibraryResources.INSTANCE.libraryJson().getText();
        Library library = Library.READER.read(json);
        assertLibrary(library);
    }


    // ------------------------------------------------------------ write tests

    public void testWrite()
    {
        // Roundtrip
        String jsonIn = LibraryResources.INSTANCE.libraryJson().getText();
        Library library = Library.READER.read(jsonIn);
        String jsonOut = Library.WRITER.toJson(library);
        JSONObject jsonObject = JSONParser.parseStrict(jsonOut).isObject();
        assertNotNull(jsonObject);
        // TODO More asserts
    }
}
