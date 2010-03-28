package name.pehl.piriti.client.gwttest.book.xml;

import java.util.List;

import name.pehl.piriti.client.gwttest.book.Book;
import name.pehl.piriti.client.gwttest.book.BookTestCase;

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


    public void testReadList()
    {
        Document document = XmlBookFactory.createBooks();
        List<Book> books = Book.XML.readList(document);
        assertBooks(books);
    }


    public void testReadListWithXpath()
    {
        Document document = XmlBookFactory.createBooks();
        List<Book> books = Book.XML.readList(document, "/books/book");
        assertBooks(books);
    }


    public void testReadListWithWrongXpath()
    {
        Document document = XmlBookFactory.createBooks();
        List<Book> books = Book.XML.readList(document, "//moo");
        assertNotNull(books);
        assertTrue(books.isEmpty());
    }
}
