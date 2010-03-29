package name.pehl.piriti.gxt.client.gwttest.book.json;

import java.util.List;

import name.pehl.piriti.client.gwttest.book.BookFactory;
import name.pehl.piriti.client.gwttest.book.json.JsonBookFactory;
import name.pehl.piriti.gxt.client.gwttest.book.Book;
import name.pehl.piriti.gxt.client.gwttest.book.BookTestCase;

/**
 * @author $Author$
 * @version $Revision$
 */
public class GwtTestJsonBookReader extends BookTestCase
{
    public void testRead()
    {
        String json = JsonBookFactory.createBook();
        Book book = Book.JSON.read(json);
        assertBook(book, true, true);
    }


    public void testReadList()
    {
        String json = JsonBookFactory.createBooks();
        List<Book> books = Book.JSON.readList(json);
        assertBooks(books, true, true);
    }


    public void testReadListWithKey()
    {
        String json = JsonBookFactory.createBooks();
        List<Book> books = Book.JSON.readList(json, BookFactory.BOOKS);
        assertBooks(books, true, true);
    }


    public void testReadListWithWrongKey()
    {
        String json = JsonBookFactory.createBooks();
        List<Book> books = Book.JSON.readList(json, "moo");
        assertNotNull(books);
        assertTrue(books.isEmpty());
    }
}
