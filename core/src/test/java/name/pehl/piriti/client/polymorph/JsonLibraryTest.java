package name.pehl.piriti.client.polymorph;

import name.pehl.totoe.json.client.JsonParser;

import com.google.gwt.json.client.JSONObject;

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
        JSONObject jsonObject = new JsonParser().parse(jsonOut);
        assertNotNull(jsonObject);
        // TODO More asserts
    }
}
