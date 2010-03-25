package name.pehl.piriti.gxt.client.gwttest.book.xml;

import name.pehl.piriti.client.gwttest.book.xml.XmlBookFactory;
import name.pehl.piriti.gxt.client.gwttest.book.Book;
import name.pehl.piriti.gxt.client.gwttest.book.BookTestCase;

import com.google.gwt.xml.client.Document;

/**
 * @author $Author$
 * @version $Revision$
 */
public class GwtTestXmlBookReader extends BookTestCase
{
    public void testRead()
    {
        Document document = XmlBookFactory.createDocument();
        Book book = Book.XML.read(document);
        assertBook(book);
    }
}
