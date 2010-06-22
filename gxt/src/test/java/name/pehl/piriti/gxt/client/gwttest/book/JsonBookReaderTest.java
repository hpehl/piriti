package name.pehl.piriti.gxt.client.gwttest.book;

import static name.pehl.piriti.client.gwttest.book.BookResources.*;

import java.util.List;

import name.pehl.piriti.client.gwttest.book.BookResources;

/**
 * @author $Author$
 * @version $Revision$
 */
public class JsonBookReaderTest extends AbstractBookReaderTest
{
    public void testRead()
    {
        String json = BookResources.INSTANCE.bookJson().getText();
        Book book = Book.JSON.read(json);
        assertBook(book, true, true);
    }


    public void testReadList()
    {
        String json = BookResources.INSTANCE.booksJson().getText();
        List<Book> books = Book.JSON.readList(json);
        assertBooks(books, true, true);
    }


    public void testReadListWithKey()
    {
        String json = BookResources.INSTANCE.booksJson().getText();
        List<Book> books = Book.JSON.readList(json, BOOKS);
        assertBooks(books, true, true);
    }


    public void testReadListWithWrongKey()
    {
        String json = BookResources.INSTANCE.booksJson().getText();
        List<Book> books = Book.JSON.readList(json, "moo");
        assertNotNull(books);
        assertTrue(books.isEmpty());
    }
}
