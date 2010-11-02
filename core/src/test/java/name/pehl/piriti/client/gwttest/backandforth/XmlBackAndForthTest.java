package name.pehl.piriti.client.gwttest.backandforth;

/**
 * @author $Author$
 * @version $Revision$
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
