package name.pehl.piriti.gxt.client.gwttest.fat.json;

import java.util.List;

import name.pehl.piriti.client.gwttest.fat.json.JsonFatGlobalItemFactory;
import name.pehl.piriti.gxt.client.gwttest.fat.FatGlobalItem;
import name.pehl.piriti.gxt.client.gwttest.fat.AbstractFatGlobalItemReaderTest;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 237 $
 */
public class JsonFatGlobalItemReaderTest extends AbstractFatGlobalItemReaderTest
{
    public void testRead()
    {
        String json = JsonFatGlobalItemFactory.createFatGlobalItem();
        FatGlobalItem fgi = FatGlobalItem.JSON.read(json);
        assertFatGlobalItem(fgi, true);
    }


    public void testReadList()
    {
        String json = JsonFatGlobalItemFactory.createFatGlobalItems();
        List<FatGlobalItem> items = FatGlobalItem.JSON.readList(json);
        assertFatGlobalItems(items, true);
    }


    public void testReadListWithKey()
    {
        String json = JsonFatGlobalItemFactory.createFatGlobalItems();
        List<FatGlobalItem> items = FatGlobalItem.JSON.readList(json, ITEMS);
        assertFatGlobalItems(items, true);
    }


    public void testReadListWithWrongKey()
    {
        String json = JsonFatGlobalItemFactory.createFatGlobalItems();
        List<FatGlobalItem> items = FatGlobalItem.JSON.readList(json, "moo");
        assertNotNull(items);
        assertTrue(items.isEmpty());
    }
}
