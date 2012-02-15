package name.pehl.piriti.client.external;

import java.util.List;

/**
 * @author $Author$
 * @version $Revision$
 */
public class XmlShopTest extends AbstractShopTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String xml = ShopResources.INSTANCE.shopXml().getText();
        Shop shop = ShopReader.SHOP_XML_READER.read(xml);
        assertShop(shop);
        assertIdsAndReferences(shop);
    }


    private void assertIdsAndReferences(Shop shop)
    {
        assertSame(shop.getCustomers().iterator().next(), shop.getOrder().getCustomer());

        int index = 0;
        String[] productIds = new String[] {"tym", "lft", "ons", "grl", "ros", "pts", "crt"};
        List<OrderItem> items = shop.getOrder().getItems();
        for (OrderItem item : items)
        {
            assertSame(findProduct(shop, productIds[index++]), item.getProduct());
        }
    }


    private Object findProduct(Shop shop, String productId)
    {
        for (Product product : shop.getProducts())
        {
            if (productId.equals(product.getId()))
            {
                return product;
            }
        }
        return null;
    }

    // ------------------------------------------------------------ write tests

    // NYI
}
