package name.pehl.piriti.client.gwttest.book.json;

import name.pehl.piriti.client.gwttest.book.Book;
import name.pehl.piriti.client.gwttest.book.BookTestCase;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 131 $
 */
public class GwtTestJsonBookReader extends BookTestCase
{
    public void testRead()
    {
        String json = JsonBookFactory.createJson();
        Book book = Book.JSON.read(json);
        assertBook(book);
    }
}
