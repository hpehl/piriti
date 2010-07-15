package name.pehl.piriti.client.gwttest.shop;

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
    }
}
