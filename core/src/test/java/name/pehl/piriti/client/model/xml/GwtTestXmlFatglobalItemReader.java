package name.pehl.piriti.client.model.xml;

import name.pehl.piriti.client.model.FatGlobalItem;
import name.pehl.piriti.client.model.FatGlobalItemTestCase;

import com.google.gwt.xml.client.Document;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 133 $
 */
public class GwtTestXmlFatglobalItemReader extends FatGlobalItemTestCase
{
    public void testRead()
    {
        Document document = XmlFatGlobalItemFactory.createDocument();
        FatGlobalItem model = FatGlobalItem.XML.read(document);
        assertFatGlobalItem(model);
    }
}
