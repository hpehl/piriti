package name.pehl.piriti.gxt.client.gwttest.shop;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 732 $
 */
public class JsonShopTest extends AbstractShopTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String json = ShopResources.INSTANCE.shopJson().getText();
        Shop shop = ShopReader.SHOP_JSON_READER.read(json);
        assertShop(shop);
    }

    // ------------------------------------------------------------- write tests

    // NYI
}
