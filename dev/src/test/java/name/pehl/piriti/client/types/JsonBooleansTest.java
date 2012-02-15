package name.pehl.piriti.client.types;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 1454 $
 */
public class JsonBooleansTest extends AbstractBooleansTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String json = BooleansResources.INSTANCE.booleansJson().getText();
        Booleans booleans = Booleans.JSON_READER.read(json);
        assertBooleans(booleans);
    }

    // ------------------------------------------------------------ write tests

    // NYI
}
