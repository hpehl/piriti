package name.pehl.piriti.gxt.client.gwttest.types;

import name.pehl.piriti.client.gwttest.types.FatGlobalItemResources;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 133 $
 */
public class XmlFatGlobalItemTest extends AbstractFatGlobalItemTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String xml = FatGlobalItemResources.INSTANCE.fatGlobalItemXml().getText();
        FatGlobalItem model = FatGlobalItem.XML_READER.read(xml);
        assertFatGlobalItem(model, true);
    }

    // ------------------------------------------------------------ write tests

    // NYI
}
