package name.pehl.piriti.gxt.client.gwttest.shop;

import name.pehl.totoe.client.Document;
import name.pehl.totoe.client.XmlParser;

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
        Document document = new XmlParser().parse(xml);
        Shop shop = ShopReader.SHOP_XML_READER.read(document);
        assertShop(shop);
    }

    // ------------------------------------------------------------- write tests

    // NYI
}
