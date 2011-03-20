package name.pehl.piriti.client.converter;

import name.pehl.totoe.json.client.JsonParser;

import com.google.gwt.json.client.JSONObject;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 1454 $
 */
public class JsonBackAndForthTest extends AbstractBackAndForthTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String json = BackAndForthResources.INSTANCE.backAndForthJson().getText();
        BackAndForth backAndForth = BackAndForth.JSON_READER.read(json);
        assertBackAndForth(backAndForth);
        assertEquals(456, backAndForth.noConverter);
    }


    // ------------------------------------------------------------ write tests

    public void testWrite()
    {
        // Roundtrip
        String jsonIn = BackAndForthResources.INSTANCE.backAndForthJson().getText();
        BackAndForth backAndForth = BackAndForth.JSON_READER.read(jsonIn);
        String jsonOut = BackAndForth.JSON_WRITER.toJson(backAndForth);
        JSONObject jsonObject = new JsonParser().parse(jsonOut);
        assertNotNull(jsonObject);
        // TODO More asserts
    }
}
