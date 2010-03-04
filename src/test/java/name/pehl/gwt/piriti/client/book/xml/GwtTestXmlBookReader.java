package name.pehl.gwt.piriti.client.book.xml;

import name.pehl.gwt.piriti.client.book.Author;
import name.pehl.gwt.piriti.client.book.Book;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.xml.client.Document;

/**
 * @author $Author$
 * @version $Revision$
 */
public class GwtTestXmlBookReader extends GWTTestCase
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
        Document document = XmlBookFactory.createBook();
        Book book = Book.XML.readSingle(document);

        assertNotNull(book);
        assertEquals(XmlBookFactory.ISBN, book.isbn);
        assertEquals(XmlBookFactory.PAGES, book.pages);
        assertEquals(XmlBookFactory.TITLE, book.title);
        assertEquals(XmlBookFactory.AUTHOR_FIRSTNAME, book.author.firstname);
        assertEquals(XmlBookFactory.AUTHOR_SURNAME, book.author.surname);
        assertEquals(XmlBookFactory.REVIEWS.length, book.reviews.size());
        for (int index = 0; index < XmlBookFactory.REVIEWS.length; index++)
        {
            assertEquals(XmlBookFactory.REVIEWS[index], book.reviews.get(index));
        }
    }
}
