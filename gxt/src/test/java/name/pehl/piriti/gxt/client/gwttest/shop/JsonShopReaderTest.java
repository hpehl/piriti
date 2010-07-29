package name.pehl.piriti.gxt.client.gwttest.shop;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 732 $
 */
public class JsonShopReaderTest extends AbstractShopReaderTest
{
    public void testRead()
    {
        String json = ShopResources.INSTANCE.shopJson().getText();
        Shop shop = ShopReader.SHOP_JSON_READER.read(json);
        assertShop(shop);
    }
}
