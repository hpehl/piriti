package name.pehl.piriti.client.types;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 1454 $
 */
public class XmlBooleansTest extends AbstractBooleansTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String xml = BooleansResources.INSTANCE.booleansXml().getText();
        Booleans booleans = Booleans.XML_READER.read(xml);
        assertBooleans(booleans);
    }

    // ------------------------------------------------------------ write tests

    // NYI
}
