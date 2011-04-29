package name.pehl.piriti.client.native_;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 131 $
 */
public class JsonResponseTest extends AbstractResponseTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String json = ResponseResources.INSTANCE.responseJson().getText();
        Response response = Response.JSON_READER.read(json);
        assertResponse(response);
        assertEquals("{\"foo\":\"bar\"}", response.resultAsString);
        assertNotNull(response.resultAsJsonValue.isObject());
        assertEquals("{\"foo\":\"bar\"}", response.resultAsJsonValue.toString());
        assertEquals("{\"foo\":\"bar\"}", response.resultAsJsonObject.toString());
        assertNull(response.resultAsNode);
        assertNull(response.resultAsElement);
    }

    // ------------------------------------------------------------ write tests

    // NYI
}
