package name.pehl.piriti.client.gwttest.book;

import static name.pehl.piriti.client.gwttest.book.BookResources.*;

import java.util.List;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 131 $
 */
public class JsonBookReaderTest extends AbstractBookReaderTest
{
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


    public void testWrite()
    {
        Book book = new Book();
        book.isbn = ISBN;
        book.pages = PAGES;
        book.title = TITLE;
        String json = Book.JSON_WRITER.toJson(book);
        assertNotNull(json);
    }
}
