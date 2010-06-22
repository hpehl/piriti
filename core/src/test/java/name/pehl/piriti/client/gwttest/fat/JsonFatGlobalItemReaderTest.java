package name.pehl.piriti.client.gwttest.fat;

import static name.pehl.piriti.client.gwttest.fat.FatGlobalItemResources.*;

import java.util.List;

/**
 * @author $Author$
 * @version $Revision$
 */
public class JsonFatGlobalItemReaderTest extends AbstractFatGlobalItemReaderTest
{
    public void testRead()
    {
        String json = FatGlobalItemResources.INSTANCE.fatGlobalItemJson().getText();
        FatGlobalItem fgi = FatGlobalItem.JSON.read(json);
        assertFatGlobalItem(fgi, true);
    }


    public void testReadList()
    {
        String json = FatGlobalItemResources.INSTANCE.fatGlobalItemsJson().getText();
        List<FatGlobalItem> items = FatGlobalItem.JSON.readList(json);
        assertFatGlobalItems(items, true);
    }


    public void testReadListWithKey()
    {
        String json = FatGlobalItemResources.INSTANCE.fatGlobalItemsJson().getText();
        List<FatGlobalItem> items = FatGlobalItem.JSON.readList(json, ITEMS);
        assertFatGlobalItems(items, true);
    }


    public void testReadListWithWrongKey()
    {
        String json = FatGlobalItemResources.INSTANCE.fatGlobalItemsJson().getText();
        List<FatGlobalItem> items = FatGlobalItem.JSON.readList(json, "moo");
        assertNotNull(items);
        assertTrue(items.isEmpty());
    }
}
