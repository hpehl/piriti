package name.pehl.piriti.client.types;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 1454 $
 */
public class JsonEnumsTest extends AbstractEnumsTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String json = EnumsResources.INSTANCE.enumsJson().getText();
        Enums enums = Enums.JSON_READER.read(json);
        assertEnums(enums);
    }

    // ------------------------------------------------------------ write tests

    // NYI
}
