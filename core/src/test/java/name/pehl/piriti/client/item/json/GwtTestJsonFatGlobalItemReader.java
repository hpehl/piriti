package name.pehl.piriti.client.item.json;

import name.pehl.piriti.client.item.FatGlobalItem;
import name.pehl.piriti.client.item.FatGlobalItemTestCase;

/**
 * @author $Author$
 * @version $Revision$
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
