package name.pehl.piriti.client.converter;

import name.pehl.totoe.xml.client.Document;
import name.pehl.totoe.xml.client.XmlParser;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 1454 $
 */
public class XmlBackAndForthTest extends AbstractBackAndForthTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String xml = BackAndForthResources.INSTANCE.backAndForthXml().getText();
        BackAndForth backAndForth = BackAndForth.XML_READER.read(xml);
        assertBackAndForth(backAndForth);
    }


    // ------------------------------------------------------------ write tests

    public void testWrite()
    {
        // Roundtrip
        String xmlIn = BackAndForthResources.INSTANCE.backAndForthXml().getText();
        BackAndForth backAndForth = BackAndForth.XML_READER.read(xmlIn);
        backAndForth.name = NameConverter.GERMAN;
        String xmlOut = BackAndForth.XML_WRITER.toXml(backAndForth);
        Document document = new XmlParser().parse(xmlOut);
        assertNotNull(document);
        // TODO More asserts
    }
}
