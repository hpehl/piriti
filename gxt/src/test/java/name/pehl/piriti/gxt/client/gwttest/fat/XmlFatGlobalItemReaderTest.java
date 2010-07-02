package name.pehl.piriti.gxt.client.gwttest.fat;

import name.pehl.piriti.client.gwttest.fat.FatGlobalItemResources;
import name.pehl.totoe.client.Document;
import name.pehl.totoe.client.XmlParser;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 133 $
 */
public class XmlFatGlobalItemReaderTest extends AbstractFatGlobalItemReaderTest
{
    public void testRead()
    {
        String xml = FatGlobalItemResources.INSTANCE.fatGlobalItemXml().getText();
        XmlParser xmlParser = new XmlParser();
        Document document = xmlParser.parse(xml);
        FatGlobalItem model = FatGlobalItem.XML.read(document);
        assertFatGlobalItem(model, true);
    }
}
