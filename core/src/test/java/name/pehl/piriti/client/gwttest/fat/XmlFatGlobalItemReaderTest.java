package name.pehl.piriti.client.gwttest.fat;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 133 $
 */
public class XmlFatGlobalItemReaderTest extends AbstractFatGlobalItemReaderTest
{
    public void testRead()
    {
        String xml = FatGlobalItemResources.INSTANCE.fatGlobalItemXml().getText();
        Document document = XMLParser.parse(xml);
        FatGlobalItem model = FatGlobalItem.XML.read(document);
        assertFatGlobalItem(model, true);
    }
}
