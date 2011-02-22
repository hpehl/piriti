package name.pehl.piriti.client.gwttest.references;

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

    // NYI
}
