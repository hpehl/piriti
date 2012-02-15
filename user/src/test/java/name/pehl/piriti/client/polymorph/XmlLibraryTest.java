package name.pehl.piriti.client.polymorph;

import name.pehl.totoe.xml.client.Document;
import name.pehl.totoe.xml.client.XmlParser;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 131 $
 */
public class XmlLibraryTest extends AbstractLibraryTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String xml = LibraryResources.INSTANCE.libraryXml().getText();
        Library library = Library.XML_READER.read(xml);
        assertLibrary(library);
    }


    // ------------------------------------------------------------ write tests

    public void testWrite()
    {
        // Roundtrip
        String xmlIn = LibraryResources.INSTANCE.libraryXml().getText();
        Library library = Library.XML_READER.read(xmlIn);
        String xmlOut = Library.XML_WRITER.toXml(library);
        Document document = new XmlParser().parse(xmlOut);
        assertNotNull(document);
        // TODO More asserts
    }
}
