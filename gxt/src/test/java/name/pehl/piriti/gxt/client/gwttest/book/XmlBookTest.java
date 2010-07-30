package name.pehl.piriti.gxt.client.gwttest.book;

import name.pehl.piriti.client.gwttest.book.BookResources;
import name.pehl.totoe.client.Document;
import name.pehl.totoe.client.XmlParser;

/**
 * @author $Author$
 * @version $Revision$
 */
public class XmlBookTest extends AbstractBookTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String xml = BookResources.INSTANCE.bookXml().getText();
        Document document = new XmlParser().parse(xml);
        Book book = Book.XML_READER.read(document);
        assertBook(book, true, true);
    }

    // ------------------------------------------------------------ write tests

    // NYI
}
