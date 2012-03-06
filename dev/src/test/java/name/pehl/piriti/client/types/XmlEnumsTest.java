package name.pehl.piriti.client.types;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 1454 $
 */
public class XmlEnumsTest extends AbstractEnumsTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String xml = EnumsResources.INSTANCE.enumsXml().getText();
        Enums enums = Enums.XML_READER.read(xml);
        assertEnums(enums);
    }

    // ------------------------------------------------------------ write tests

    // NYI
}
