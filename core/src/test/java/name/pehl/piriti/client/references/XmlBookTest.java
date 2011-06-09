package name.pehl.piriti.client.references;

import name.pehl.totoe.xml.client.Document;
import name.pehl.totoe.xml.client.XmlParser;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 133 $
 */
public class XmlBookTest extends AbstractBookTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String xml = BookResources.INSTANCE.bookXml().getText();
        Book book = Book.XML_READER.read(xml);
        assertBook(book, true, true);
    }


    // ------------------------------------------------------------ write tests

    public void testWriteBook()
    {
        // Roundtrip
        String xmlIn = BookResources.INSTANCE.bookXml().getText();
        Book book = Book.XML_READER.read(xmlIn);
        String xmlOut = Book.XML_WRITER.toXml(book);
        Document document = new XmlParser().parse(xmlOut);
        assertNotNull(document);
        // TODO More asserts
    }
}
