package name.pehl.piriti.client.gwttest.book.json;

import java.util.List;

import name.pehl.piriti.client.gwttest.book.Book;
import name.pehl.piriti.client.gwttest.book.BookFactory;
import name.pehl.piriti.client.gwttest.book.AbstractBookReaderTest;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 131 $
 */
public class JsonBookReaderTest extends AbstractBookReaderTest
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
