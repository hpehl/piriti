package name.pehl.piriti.client.gwttest.book;

import java.util.List;

import name.pehl.piriti.client.xml.Node;
import name.pehl.piriti.client.xml.XmlGinjector;
import name.pehl.piriti.client.xml.XmlParser;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 133 $
 */
public class XmlBookReaderTest extends AbstractBookReaderTest
{
    public void testRead()
    {
        String xml = BookResources.INSTANCE.bookXml().getText();
        XmlParser xmlParser = XmlGinjector.INJECTOR.getXmlParser();
        Node node = xmlParser.parse(xml);
        Book book = Book.XML.read(node);
        assertBook(book, true, true);
    }


    public void testReadList()
    {
        String xml = BookResources.INSTANCE.booksXml().getText();
        XmlParser xmlParser = XmlGinjector.INJECTOR.getXmlParser();
        Node node = xmlParser.parse(xml);
        List<Book> books = Book.XML.readList(node);
        assertBooks(books, true, true);
    }


    public void testReadListWithXpath()
    {
        String xml = BookResources.INSTANCE.booksXml().getText();
        XmlParser xmlParser = XmlGinjector.INJECTOR.getXmlParser();
        Node node = xmlParser.parse(xml);
        List<Book> books = Book.XML.readList(node, "/books/book");
        assertBooks(books, true, true);
    }


    public void testReadListWithWrongXpath()
    {
        String xml = BookResources.INSTANCE.booksXml().getText();
        XmlParser xmlParser = XmlGinjector.INJECTOR.getXmlParser();
        Node node = xmlParser.parse(xml);
        List<Book> books = Book.XML.readList(node, "//moo");
        assertNotNull(books);
        assertTrue(books.isEmpty());
    }
}
