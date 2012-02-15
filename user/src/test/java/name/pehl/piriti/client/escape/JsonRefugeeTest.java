package name.pehl.piriti.client.escape;

import name.pehl.piriti.client.AbstractPiritiTest;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;

/**
 * JsonWriter tests (special characters, escaping, ...)
 * 
 * @author $Author: harald.pehl $
 * @version $Revision: 1095 $
 */
public class JsonRefugeeTest extends AbstractPiritiTest
{
    // -------------------------------------------------------------- constants

    static final String DATA = "A \"quoted\" string with \u00fbnicode, control characters like \n\t\r and a slash: \"\\\"";


    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String json = RefugeeResources.INSTANCE.refugeeJson().getText();
        Refugee refugee = Refugee.JSON_READER.read(json);
        assertRefugee(refugee);
    }


    void assertRefugee(Refugee refugee)
    {
        assertEquals(DATA, refugee.data);
        assertEquals(DATA, refugee.converterData);
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
