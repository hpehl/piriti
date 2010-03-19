package name.pehl.piriti.gxt.client.book.json;

import name.pehl.piriti.client.book.json.JsonBookFactory;
import name.pehl.piriti.gxt.client.book.BookModel;
import name.pehl.piriti.gxt.client.book.BookModelTestCase;

/**
 * @author $Author$
 * @version $Revision$
 */
public class GwtTestJsonBookModelReader extends BookModelTestCase
{
    public void testRead()
    {
        String json = JsonBookFactory.createJson();
        BookModel book = BookModel.JSON.read(json);
        assertBookModel(book);
    }
}
