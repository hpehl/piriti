package name.pehl.piriti.client.gwttest.fat.json;

import java.util.List;

import name.pehl.piriti.client.gwttest.fat.FatGlobalItem;
import name.pehl.piriti.client.gwttest.fat.FatGlobalItemTestCase;

/**
 * @author $Author$
 * @version $Revision$
 */
public class GwtTestJsonFatGlobalItemReader extends FatGlobalItemTestCase
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
