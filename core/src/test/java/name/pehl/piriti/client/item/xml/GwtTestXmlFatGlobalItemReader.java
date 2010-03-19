package name.pehl.piriti.client.item.xml;

import name.pehl.piriti.client.item.FatGlobalItem;
import name.pehl.piriti.client.item.FatGlobalItemTestCase;

import com.google.gwt.xml.client.Document;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 133 $
 */
public class GwtTestXmlFatGlobalItemReader extends FatGlobalItemTestCase
{
    public void testRead()
    {
        Document document = XmlFatGlobalItemFactory.createDocument();
        FatGlobalItem model = FatGlobalItem.XML.read(document);
        assertFatGlobalItem(model);
    }
}
