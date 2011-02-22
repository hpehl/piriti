package name.pehl.piriti.gxt.client.gwttest.external;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 736 $
 */
public class XmlShopTest extends AbstractShopTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String xml = ShopResources.INSTANCE.shopXml().getText();
        Shop shop = ShopReader.SHOP_XML_READER.read(xml);
        assertShop(shop);
    }

    // ------------------------------------------------------------- write tests

    // NYI
}
