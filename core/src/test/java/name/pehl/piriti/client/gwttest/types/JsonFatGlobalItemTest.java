package name.pehl.piriti.client.gwttest.types;

/**
 * @author $Author$
 * @version $Revision$
 */
public class JsonFatGlobalItemTest extends AbstractFatGlobalItemTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String json = FatGlobalItemResources.INSTANCE.fatGlobalItemJson().getText();
        FatGlobalItem fgi = FatGlobalItem.JSON_READER.read(json);
        assertFatGlobalItem(fgi, true);
    }

    // ------------------------------------------------------------ write tests

    // NYI
}
