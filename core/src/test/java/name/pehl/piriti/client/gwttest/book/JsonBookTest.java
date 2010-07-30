package name.pehl.piriti.client.gwttest.book;

import static name.pehl.piriti.client.gwttest.book.BookResources.*;

import java.util.List;

import name.pehl.piriti.client.json.JsonGinjector;
import name.pehl.piriti.client.json.JsonParser;

import com.google.gwt.json.client.JSONObject;

/**
 * This class contains methods to test reading and writing <code>null</code> and
 * empty values. All other tests omit these tests as they're tested here.
 * 
 * @author $Author: harald.pehl $
 * @version $Revision: 131 $
 */
public class JsonBookTest extends AbstractBookReaderTest
{
    private static final String SPACES = "          ";


    // ------------------------------------------------------------- read tests

    // public void testReadNull()
    // {
    // Book book = Book.JSON_READER.read((String)null);
    // assertNull(book);
    // book = Book.JSON_READER.read((JSONObject) null);
    // assertNull(book);
    // }

    // public void testReadEmpty()
    // {
    // Book book = Book.JSON_READER.read("");
    // assertNull(book);
    // book = Book.JSON_READER.read(SPACES);
    // assertNull(book);
    // book = Book.JSON_READER.read(new JSONObject());
    // assertNotNull(book);
    // }

    public void testRead()
    {
        String json = BookResources.INSTANCE.bookJson().getText();
        Book book = Book.JSON_READER.read(json);
        assertBook(book, true, true);
    }


    // public void testReadListNull()
    // {
    // List<Book> books = Book.JSON_READER.readList((JSONArray)null);
    // assertNull(books);
    // books = Book.JSON_READER.readList((JSONObject)null);
    // assertNull(books);
    // books = Book.JSON_READER.readList((JSONObject)null, null);
    // assertNull(books);
    // books = Book.JSON_READER.readList((JSONObject)null, "");
    // assertNull(books);
    // books = Book.JSON_READER.readList((JSONObject)null, SPACES);
    // assertNull(books);
    // books = Book.JSON_READER.readList((String)null);
    // assertNull(books);
    // books = Book.JSON_READER.readList((String)null, null);
    // assertNull(books);
    // books = Book.JSON_READER.readList((String)null, "");
    // assertNull(books);
    // books = Book.JSON_READER.readList((String)null, SPACES);
    // assertNull(books);
    // }

    // public void testReadListEmpty()
    // {
    // List<Book> books = Book.JSON_READER.readList(new JSONArray());
    // assertTrue(books.isEmpty());
    // books = Book.JSON_READER.readList(new JSONObject());
    // assertTrue(books.isEmpty());
    // books = Book.JSON_READER.readList(new JSONObject(), null);
    // assertTrue(books.isEmpty());
    // books = Book.JSON_READER.readList(new JSONObject(), "");
    // assertTrue(books.isEmpty());
    // books = Book.JSON_READER.readList(new JSONObject(), SPACES);
    // assertTrue(books.isEmpty());
    // books = Book.JSON_READER.readList("");
    // assertNull(books);
    // books = Book.JSON_READER.readList("", null);
    // assertNull(books);
    // books = Book.JSON_READER.readList("", "");
    // assertNull(books);
    // books = Book.JSON_READER.readList("", SPACES);
    // assertNull(books);
    // }

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
