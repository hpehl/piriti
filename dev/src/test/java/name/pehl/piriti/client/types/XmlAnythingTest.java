package name.pehl.piriti.client.types;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 1454 $
 */
public class XmlAnythingTest extends AbstractAnythingTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String xml = AnythingResources.INSTANCE.anythingXml().getText();
        Anything anything = Anything.XML_READER.read(xml);
        assertAnything(anything);
    }

    // ------------------------------------------------------------ write tests

    // NYI
}
