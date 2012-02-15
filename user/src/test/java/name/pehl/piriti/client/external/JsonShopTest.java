package name.pehl.piriti.client.external;

/**
 * @author $Author$
 * @version $Revision$
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

    // ------------------------------------------------------------ write tests

    // NYI
}
