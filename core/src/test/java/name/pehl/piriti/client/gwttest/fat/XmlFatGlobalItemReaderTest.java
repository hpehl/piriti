package name.pehl.piriti.client.gwttest.fat;

import name.pehl.totoe.client.Document;
import name.pehl.totoe.client.XmlParser;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 133 $
 */
public class XmlFatGlobalItemReaderTest extends AbstractFatGlobalItemTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String xml = FatGlobalItemResources.INSTANCE.fatGlobalItemXml().getText();
        Document document = new XmlParser().parse(xml);
        FatGlobalItem model = FatGlobalItem.XML_READER.read(document);
        assertFatGlobalItem(model, true);
    }

    // ------------------------------------------------------------ writer tests

    // NYI
}
