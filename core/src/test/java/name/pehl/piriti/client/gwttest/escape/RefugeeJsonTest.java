package name.pehl.piriti.client.gwttest.escape;

import name.pehl.totoe.json.client.JsonParser;

import com.google.gwt.json.client.JSONObject;

/**
 * JsonWriter tests (special characters, escaping, ...)
 * 
 * @author $Author: harald.pehl $
 * @version $Revision: 1095 $
 */
public class RefugeeJsonTest extends AbstractRefugeeTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String json = RefugeeResources.INSTANCE.refugeeJson().getText();
        Refugee refugee = Refugee.JSON_READER.read(json);
        assertRefugee(refugee);
    }


    private void assertRefugee(Refugee refugee)
    {
        assertEquals(RefugeeResources.JSON_STRING, refugee.data);
        assertEquals(RefugeeResources.JSON_STRING, refugee.converterData);
    }


    // ------------------------------------------------------------ write tests

    public void testWrite()
    {
        // Roundtrip
        String jsonIn = RefugeeResources.INSTANCE.refugeeJson().getText();
        Refugee refugee = Refugee.JSON_READER.read(jsonIn);
        String jsonOut = Refugee.JSON_WRITER.toJson(refugee);
        JSONObject jsonObject = new JsonParser().parse(jsonOut);
        assertNotNull(jsonObject);
        // TODO More asserts
    }
}
