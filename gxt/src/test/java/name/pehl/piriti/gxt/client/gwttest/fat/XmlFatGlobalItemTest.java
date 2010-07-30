package name.pehl.piriti.gxt.client.gwttest.fat;

import name.pehl.piriti.client.gwttest.fat.FatGlobalItemResources;
import name.pehl.totoe.client.Document;
import name.pehl.totoe.client.XmlParser;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 133 $
 */
public class XmlFatGlobalItemTest extends AbstractFatGlobalItemTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String xml = FatGlobalItemResources.INSTANCE.fatGlobalItemXml().getText();
        Document document = new XmlParser().parse(xml);
        FatGlobalItem model = FatGlobalItem.XML_READER.read(document);
        assertFatGlobalItem(model, true);
    }

    // ------------------------------------------------------------ write tests

    // NYI
}
