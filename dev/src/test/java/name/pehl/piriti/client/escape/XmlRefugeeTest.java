package name.pehl.piriti.client.escape;

import name.pehl.piriti.client.AbstractPiritiTest;
import name.pehl.totoe.xml.client.Document;
import name.pehl.totoe.xml.client.XmlParser;

/**
 * JsonWriter tests (special characters, escaping, ...)
 * 
 * @author $Author: harald.pehl $
 * @version $Revision: 1095 $
 */
public class XmlRefugeeTest extends AbstractPiritiTest
{
    // -------------------------------------------------------------- constants

    static final String DATA = "A string with <xml/> markup like \" & '";


    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String xml = RefugeeResources.INSTANCE.refugeeXml().getText();
        Refugee refugee = Refugee.XML_READER.read(xml);
        assertRefugee(refugee);
    }


    void assertRefugee(Refugee refugee)
    {
        assertEquals(DATA, refugee.data);
        assertEquals(DATA, refugee.converterData);
    }


    // ------------------------------------------------------------ write tests

    public void testWrite()
    {
        // Roundtrip
        String xmlIn = RefugeeResources.INSTANCE.refugeeXml().getText();
        Refugee refugee = Refugee.XML_READER.read(xmlIn);
        String xmlOut = Refugee.XML_WRITER.toXml(refugee);
        Document document = new XmlParser().parse(xmlOut);
        assertNotNull(document);
        // TODO More asserts
    }
}
