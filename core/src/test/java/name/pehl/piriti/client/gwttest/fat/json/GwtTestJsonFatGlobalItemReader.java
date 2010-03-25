package name.pehl.piriti.client.gwttest.fat.json;

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
        String json = JsonFatGlobalItemFactory.createJson();
        FatGlobalItem fgi = FatGlobalItem.JSON.read(json);
        assertFatGlobalItem(fgi);
    }
}
