package name.pehl.piriti.client.gwttest.fat.xml;

import java.util.List;

import name.pehl.piriti.client.gwttest.fat.FatGlobalItem;
import name.pehl.piriti.client.gwttest.fat.FatGlobalItemTestCase;

import com.google.gwt.xml.client.Document;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 133 $
 */
public class GwtTestXmlFatGlobalItemReader extends FatGlobalItemTestCase
{
    public void testRead()
    {
        Document document = XmlFatGlobalItemFactory.createFatGlobalItem();
        FatGlobalItem model = FatGlobalItem.XML.read(document);
        assertFatGlobalItem(model);
    }


    public void testReadList()
    {
        Document document = XmlFatGlobalItemFactory.createFatGlobalItems();
        List<FatGlobalItem> items = FatGlobalItem.XML.readList(document);
        assertFatGlobalItems(items);
    }


    public void testReadListWithXpath()
    {
        Document document = XmlFatGlobalItemFactory.createFatGlobalItems();
        List<FatGlobalItem> items = FatGlobalItem.XML.readList(document, "//fatGlobalItem");
        assertFatGlobalItems(items);
    }


    public void testReadListWithWrongXpath()
    {
        Document document = XmlFatGlobalItemFactory.createFatGlobalItems();
        List<FatGlobalItem> items = FatGlobalItem.XML.readList(document, "//moo");
        assertNotNull(items);
        assertTrue(items.isEmpty());
    }
}
