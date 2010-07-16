package name.pehl.piriti.client.gwttest.shop;

import java.util.SortedSet;

import name.pehl.totoe.client.Document;
import name.pehl.totoe.client.XmlParser;

/**
 * @author $Author$
 * @version $Revision$
 */
public class XmlShopReaderTest extends AbstractShopReaderTest
{
    public void testRead()
    {
        String xml = ShopResources.INSTANCE.shopXml().getText();
        Document document = new XmlParser().parse(xml);
        Shop shop = ShopReader.SHOP_XML.read(document);
        assertShop(shop);
        assertIdsAndReferences(shop);
    }


    private void assertIdsAndReferences(Shop shop)
    {
        assertSame(shop.getCustomers().iterator().next(), shop.getOrder().getCustomer());

        int index = 0;
        String[] productIds = new String[] {"tym", "lft", "ons", "grl", "ros", "pts", "crt"};
        SortedSet<OrderItem> items = shop.getOrder().getItems();
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
}
