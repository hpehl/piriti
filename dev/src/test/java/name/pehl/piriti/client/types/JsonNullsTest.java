package name.pehl.piriti.client.types;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 1454 $
 */
public class JsonNullsTest extends AbstractNullsTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String json = NullsResources.INSTANCE.nullsJson().getText();
        Nulls nulls = Nulls.JSON_READER.read(json);
        assertNulls(nulls);
    }


    // ------------------------------------------------------------ write tests

    public void testWrite()
    {
        Nulls nulls = new Nulls("hello", null, 23, null, 4.2, null);
        String json = Nulls.JSON_WRITER.toJson(nulls);
        assertEquals(NullsResources.INSTANCE.nullsCompactJson().getText(), json);
    }
}
