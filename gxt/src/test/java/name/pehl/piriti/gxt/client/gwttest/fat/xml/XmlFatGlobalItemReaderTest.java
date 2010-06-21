package name.pehl.piriti.gxt.client.gwttest.fat.xml;

import name.pehl.piriti.client.gwttest.fat.xml.XmlFatGlobalItemFactory;
import name.pehl.piriti.gxt.client.gwttest.fat.FatGlobalItem;
import name.pehl.piriti.gxt.client.gwttest.fat.AbstractFatGlobalItemReaderTest;

import com.google.gwt.xml.client.Document;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 133 $
 */
public class XmlFatGlobalItemReaderTest extends AbstractFatGlobalItemReaderTest
{
    public void testRead()
    {
        Document document = XmlFatGlobalItemFactory.createFatGlobalItem();
        FatGlobalItem model = FatGlobalItem.XML.read(document);
        assertFatGlobalItem(model, true);
    }
}
