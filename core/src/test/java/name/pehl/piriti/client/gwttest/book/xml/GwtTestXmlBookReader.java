package name.pehl.piriti.client.gwttest.book.xml;

import java.util.List;

import name.pehl.piriti.client.gwttest.book.Book;
import name.pehl.piriti.client.gwttest.book.BookTestCase;
import name.pehl.piriti.client.xml.sarissa.Node;
import name.pehl.piriti.client.xml.sarissa.XML;
import name.pehl.piriti.client.xml.sarissa.XMLParseException;

import com.google.gwt.xml.client.Document;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 133 $
 */
public class GwtTestXmlBookReader extends BookTestCase
{
    public void testRead()
    {
        try
        {
            Node document = XML.parse("<root><child>text</child></root>");
            Node node = document.getNode("/root/child/text()");
            String value = node.getStringValue();
            System.out.println("Value: " + value);
        }
        catch (XMLParseException e)
        {
            System.out.println(e.getMessage());
        }
        Document document = XmlBookFactory.createBook();
        Book book = Book.XML.read(document);
        assertBook(book, true, true);
    }


    public void testReadList()
    {
        Document document = XmlBookFactory.createBooks();
        List<Book> books = Book.XML.readList(document);
        assertBooks(books, true, true);
    }


    public void testReadListWithXpath()
    {
        Document document = XmlBookFactory.createBooks();
        List<Book> books = Book.XML.readList(document, "/books/book");
        assertBooks(books, true, true);
    }


    public void testReadListWithWrongXpath()
    {
        Document document = XmlBookFactory.createBooks();
        List<Book> books = Book.XML.readList(document, "//moo");
        assertNotNull(books);
        assertTrue(books.isEmpty());
    }
}
