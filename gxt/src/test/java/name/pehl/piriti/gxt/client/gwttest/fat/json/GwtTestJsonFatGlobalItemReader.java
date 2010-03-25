package name.pehl.piriti.gxt.client.fat.json;

import name.pehl.piriti.client.fat.json.JsonFatGlobalItemFactory;
import name.pehl.piriti.gxt.client.fat.FatGlobalItem;
import name.pehl.piriti.gxt.client.fat.FatGlobalItemTestCase;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 237 $
 */
public class GwtTestJsonFatGlobalItemReader extends FatGlobalItemTestCase
{
    public void testRead()
    {
        String json = JsonFatGlobalItemFactory.createJson();
        FatGlobalItem fgi = FatGlobalItem.JSON.read(json);
        assertFatGlobalItem(fgi);
    }
}
