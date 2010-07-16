package name.pehl.piriti.gxt.client.gwttest.shop;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 732 $
 */
public class JsonShopReaderTest extends AbstractShopReaderTest
{
    public void testRead()
    {
        String json = name.pehl.piriti.client.gwttest.shop.ShopResources.INSTANCE.shopJson().getText();
        Shop shop = ShopReader.SHOP_JSON.read(json);
        assertShop(shop);
    }
}
