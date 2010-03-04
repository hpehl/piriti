package name.pehl.gwt.piriti.client.book.json;

import name.pehl.gwt.piriti.client.book.Author;
import name.pehl.gwt.piriti.client.book.Book;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 131 $
 */
public class GwtTestJsonBookReader extends GWTTestCase
{
    @Override
    public String getModuleName()
    {
        return "name.pehl.gwt.piriti.PiritiTest";
    }


    @Override
    protected void gwtSetUp() throws Exception
    {
        // Register the JsonReaders
        new Book();
        new Author();
    }


    public void testReadSingle()
    {
        // String json = JsonBookFactory.createBook();
        // Book book = Book.JSON.readSingle(json);
        //
        // assertNotNull(book);
        // assertEquals(JsonBookFactory.ISBN, book.isbn);
        // assertEquals(JsonBookFactory.PAGES, book.pages);
        // assertEquals(JsonBookFactory.TITLE, book.title);
        // assertEquals(JsonBookFactory.AUTHOR_FIRSTNAME,
        // book.author.firstname);
        // assertEquals(JsonBookFactory.AUTHOR_SURNAME, book.author.surname);
        // assertEquals(JsonBookFactory.REVIEWS.length, book.reviews.size());
        // for (int index = 0; index < JsonBookFactory.REVIEWS.length; index++)
        // {
        // assertEquals(JsonBookFactory.REVIEWS[index],
        // book.reviews.get(index));
        // }
    }
}
