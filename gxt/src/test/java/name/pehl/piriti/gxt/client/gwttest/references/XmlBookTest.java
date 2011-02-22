package name.pehl.piriti.gxt.client.gwttest.references;

import name.pehl.piriti.client.gwttest.references.BookResources;

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
        Book book = Book.XML_READER.read(xml);
        assertBook(book, true, true);
    }

    // ------------------------------------------------------------ write tests

    // NYI
}
