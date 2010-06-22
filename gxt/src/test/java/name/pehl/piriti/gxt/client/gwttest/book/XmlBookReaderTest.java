package name.pehl.piriti.gxt.client.gwttest.book;

import java.util.List;

import name.pehl.piriti.client.gwttest.book.BookResources;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;

/**
 * @author $Author$
 * @version $Revision$
 */
public class XmlBookReaderTest extends AbstractBookReaderTest
{
    public void testRead()
    {
        String xml = BookResources.INSTANCE.bookXml().getText();
        Document document = XMLParser.parse(xml);
        Book book = Book.XML.read(document);
        assertBook(book, true, true);
    }


    public void testReadList()
    {
        String xml = BookResources.INSTANCE.booksXml().getText();
        Document document = XMLParser.parse(xml);
        List<Book> books = Book.XML.readList(document);
        assertBooks(books, true, true);
    }


    public void testReadListWithXpath()
    {
        String xml = BookResources.INSTANCE.booksXml().getText();
        Document document = XMLParser.parse(xml);
        List<Book> books = Book.XML.readList(document, "/books/book");
        assertBooks(books, true, true);
    }


    public void testReadListWithWrongXpath()
    {
        String xml = BookResources.INSTANCE.booksXml().getText();
        Document document = XMLParser.parse(xml);
        List<Book> books = Book.XML.readList(document, "//moo");
        assertNotNull(books);
        assertTrue(books.isEmpty());
    }
}
