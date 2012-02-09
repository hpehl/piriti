package name.pehl.piriti.client.escape;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;

/**
 * JsonWriter tests (special characters, escaping, ...)
 * 
 * @author $Author: harald.pehl $
 * @version $Revision: 1095 $
 */
public class JsonRefugeeTest extends AbstractRefugeeTest
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
        JSONObject jsonObject = JSONParser.parseStrict(jsonOut).isObject();
        assertNotNull(jsonObject);
        // TODO More asserts
    }
}
