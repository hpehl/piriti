package name.pehl.piriti.client.types;

import name.pehl.totoe.json.client.JsonParser;

import com.google.gwt.json.client.JSONObject;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 1454 $
 */
public class JsonAnythingTest extends AbstractAnythingTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String json = AnythingResources.INSTANCE.anythingJson().getText();
        Anything anything = Anything.JSON_READER.read(json);
        assertAnything(anything);
    }


    // ------------------------------------------------------------ write tests

    public void testWrite()
    {
        // Roundtrip
        String jsonIn = AnythingResources.INSTANCE.anythingJson().getText();
        Anything anything = Anything.JSON_READER.read(jsonIn);
        String jsonOut = Anything.JSON_WRITER.toJson(anything);
        JSONObject jsonObject = new JsonParser().parse(jsonOut);
        assertNotNull(jsonObject);
        // TODO More asserts
    }
}
