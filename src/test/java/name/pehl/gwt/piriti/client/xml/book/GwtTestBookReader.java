package name.pehl.gwt.piriti.client.xml.book;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.xml.client.Document;

/**
 * @author $Author$
 * @version $Revision$
 */
public class GwtTestBookReader extends GWTTestCase
{
    @Override
    public String getModuleName()
    {
        return "name.pehl.gwt.piriti.PiritiTest";
    }


    @Override
    protected void gwtSetUp() throws Exception
    {
        // Register the XmlReaders
        new Book();
        new Author();
    }


    public void testReadSingle()
    {
        Document document = BookXmlFactory.createBookDocument("book");
        Book book = Book.XML.readSingle(document);

        assertNotNull(book);
        assertEquals(BookXmlFactory.ISBN, book.isbn);
        assertEquals(BookXmlFactory.PAGES, book.pages);
        assertEquals(BookXmlFactory.TITLE, book.title);
        assertEquals(BookXmlFactory.AUTHOR_FIRSTNAME, book.author.firstname);
        assertEquals(BookXmlFactory.AUTHOR_SURNAME, book.author.surname);
        assertEquals(BookXmlFactory.REVIEWS.length, book.reviews.size());
        for (int index = 0; index < BookXmlFactory.REVIEWS.length; index++)
        {
            assertEquals(BookXmlFactory.REVIEWS[index], book.reviews.get(index));
        }
    }
}
