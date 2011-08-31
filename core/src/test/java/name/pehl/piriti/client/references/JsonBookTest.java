package name.pehl.piriti.client.references;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 131 $
 */
public class JsonBookTest extends AbstractBookTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String json = BookResources.INSTANCE.bookJson().getText();
        Book book = Book.JSON_READER.read(json);
        assertBook(book, true, true);
    }


    // ------------------------------------------------------------ write tests

    public void testWrite()
    {
        // Roundtrip
        String jsonIn = BookResources.INSTANCE.bookJson().getText();
        Book book = Book.JSON_READER.read(jsonIn);
        String jsonOut = Book.JSON_WRITER.toJson(book);
        JSONObject jsonObject = JSONParser.parseStrict(jsonOut).isObject();
        assertNotNull(jsonObject);
        // TODO More asserts
    }
}
