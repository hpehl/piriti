package name.pehl.piriti.gxt.client.gwttest.book;

import name.pehl.piriti.client.gwttest.book.BookResources;

/**
 * @author $Author$
 * @version $Revision$
 */
public class JsonBookTest extends AbstractBookTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String json = BookResources.INSTANCE.bookJson().getText();
        Book book = Book.JSON_READER.read(json);
        assertBook(book, true, true);
    }

    // ------------------------------------------------------------ write tests

    // NYI
}
