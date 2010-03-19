package name.pehl.piriti.gxt.client.book.xml;

import name.pehl.piriti.client.book.xml.XmlBookFactory;
import name.pehl.piriti.gxt.client.book.Book;
import name.pehl.piriti.gxt.client.book.BookTestCase;

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
