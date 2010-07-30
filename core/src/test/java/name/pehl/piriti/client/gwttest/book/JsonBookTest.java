package name.pehl.piriti.client.gwttest.book;

import static name.pehl.piriti.client.gwttest.book.BookResources.*;

import java.util.List;

import name.pehl.piriti.client.json.JsonGinjector;
import name.pehl.piriti.client.json.JsonParser;

import com.google.gwt.json.client.JSONObject;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 131 $
 */
public class JsonBookTest extends AbstractBookReaderTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String json = BookResources.INSTANCE.bookJson().getText();
        Book book = Book.JSON_READER.read(json);
        assertBook(book, true, true);
    }


    public void testReadList()
    {
        String json = BookResources.INSTANCE.booksJson().getText();
        List<Book> books = Book.JSON_READER.readList(json);
        assertBooks(books, true, true);
    }


    public void testReadListWithKey()
    {
        String json = BookResources.INSTANCE.booksJson().getText();
        List<Book> books = Book.JSON_READER.readList(json, BOOKS);
        assertBooks(books, true, true);
    }


    public void testReadListWithWrongKey()
    {
        String json = BookResources.INSTANCE.booksJson().getText();
        List<Book> books = Book.JSON_READER.readList(json, "moo");
        assertNotNull(books);
        assertTrue(books.isEmpty());
    }


    // ------------------------------------------------------------ write tests

    public void testWriteNullBook()
    {
        String json = Book.JSON_WRITER.toJson(null);
        assertNull(json);
    }


    public void testWriteEmptyBook()
    {
        String json = Book.JSON_WRITER.toJson(new Book());
        JsonParser jsonParser = JsonGinjector.INJECTOR.getJsonParser();
        JSONObject jsonObject = jsonParser.parse(json);
        assertNotNull(jsonObject);
        // TODO More asserts
    }


    public void testWriteBook()
    {
        // Roundtrip
        String jsonIn = BookResources.INSTANCE.bookJson().getText();
        Book book = Book.JSON_READER.read(jsonIn);
        String jsonOut = Book.JSON_WRITER.toJson(book);
        JsonParser jsonParser = JsonGinjector.INJECTOR.getJsonParser();
        JSONObject jsonObject = jsonParser.parse(jsonOut);
        assertNotNull(jsonObject);
        // TODO More asserts
    }
}
