package name.pehl.piriti.client.gwttest.book;

import java.util.List;

import name.pehl.totoe.client.Document;
import name.pehl.totoe.client.XmlParser;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 133 $
 */
public class XmlBookReaderTest extends AbstractBookReaderTest
{
    public void testRead()
    {
        String xml = BookResources.INSTANCE.bookXml().getText();
        XmlParser xmlParser = new XmlParser();
        Document document = xmlParser.parse(xml);
        Book book = Book.XML.read(document);
        assertBook(book, true, true);
    }


    public void testReadList()
    {
        String xml = BookResources.INSTANCE.booksXml().getText();
        XmlParser xmlParser = new XmlParser();
        Document document = xmlParser.parse(xml);
        List<Book> books = Book.XML.readList(document);
        assertBooks(books, true, true);
    }


    public void testReadListWithXpath()
    {
        String xml = BookResources.INSTANCE.booksXml().getText();
        XmlParser xmlParser = new XmlParser();
        Document document = xmlParser.parse(xml);
        List<Book> books = Book.XML.readList(document, "/books/book");
        assertBooks(books, true, true);
    }


    public void testReadListWithWrongXpath()
    {
        String xml = BookResources.INSTANCE.booksXml().getText();
        XmlParser xmlParser = new XmlParser();
        Document document = xmlParser.parse(xml);
        List<Book> books = Book.XML.readList(document, "//moo");
        assertNotNull(books);
        assertTrue(books.isEmpty());
    }
}
