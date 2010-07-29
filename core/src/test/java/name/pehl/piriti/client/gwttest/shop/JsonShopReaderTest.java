package name.pehl.piriti.client.gwttest.shop;

/**
 * @author $Author$
 * @version $Revision$
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
