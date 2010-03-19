package name.pehl.piriti.gxt.client.book.json;

import name.pehl.piriti.client.book.json.JsonBookFactory;
import name.pehl.piriti.gxt.client.book.Book;
import name.pehl.piriti.gxt.client.book.BookTestCase;

/**
 * @author $Author$
 * @version $Revision$
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
