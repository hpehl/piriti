package name.pehl.piriti.client.gwttest.references;

import name.pehl.totoe.json.client.JsonParser;

import com.google.gwt.json.client.JSONObject;

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

    public void testWriteBook()
    {
        // Roundtrip
        String jsonIn = BookResources.INSTANCE.bookJson().getText();
        Book book = Book.JSON_READER.read(jsonIn);
        String jsonOut = Book.JSON_WRITER.toJson(book);
        JSONObject jsonObject = new JsonParser().parse(jsonOut);
        assertNotNull(jsonObject);
        // TODO More asserts
    }
}
