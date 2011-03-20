package name.pehl.piriti.client.converter;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 1454 $
 */
public class XmlBackAndForthTest extends AbstractBackAndForthTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String xml = BackAndForthResources.INSTANCE.backAndForthXml().getText();
        BackAndForth backAndForth = BackAndForth.XML_READER.read(xml);
        assertBackAndForth(backAndForth);
    }

    // ------------------------------------------------------------ write tests

    // NYI
}
