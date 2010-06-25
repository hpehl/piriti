package name.pehl.piriti.gxt.client.gwttest.fat;

import name.pehl.piriti.client.gwttest.fat.FatGlobalItemResources;
import name.pehl.piriti.client.xml.Node;
import name.pehl.piriti.client.xml.XmlGinjector;
import name.pehl.piriti.client.xml.XmlParser;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 133 $
 */
public class XmlFatGlobalItemReaderTest extends AbstractFatGlobalItemReaderTest
{
    public void testRead()
    {
        String xml = FatGlobalItemResources.INSTANCE.fatGlobalItemXml().getText();
        XmlParser xmlParser = XmlGinjector.INJECTOR.getXmlParser();
        Node node = xmlParser.parse(xml);
        FatGlobalItem model = FatGlobalItem.XML.read(node);
        assertFatGlobalItem(model, true);
    }
}
