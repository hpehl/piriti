package name.pehl.piriti.gxt.client.gwttest.book.json;

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
        String json = JsonBookFactory.createJson();
        Book book = Book.JSON.read(json);
        assertBook(book);
    }
}
