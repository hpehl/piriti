package name.pehl.piriti.client.book.xml;

import name.pehl.piriti.client.book.Book;
import name.pehl.piriti.client.book.BookTestCase;

import com.google.gwt.xml.client.Document;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 133 $
 */
public class GwtTestXmlBookReader extends BookTestCase
{
    public void testRead()
    {
        Document document = XmlBookFactory.createBook();
        Book book = Book.XML.read(document);
        assertBook(book);
    }
}
