package name.pehl.piriti.client.book.json;

import name.pehl.piriti.client.book.Book;
import name.pehl.piriti.client.book.BookTestCase;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 131 $
 */
public class GwtTestJsonBookReader extends BookTestCase
{
    public void testRead()
    {
        String json = JsonBookFactory.createBook();
        Book book = Book.JSON.read(json);
        // Not yet implemented
        // assertBook(book);
    }
}
