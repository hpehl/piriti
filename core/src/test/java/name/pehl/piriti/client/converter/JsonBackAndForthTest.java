package name.pehl.piriti.client.converter;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;

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
    }


    // ------------------------------------------------------------ write tests

    public void testWrite()
    {
        // Roundtrip
        String jsonIn = BackAndForthResources.INSTANCE.backAndForthJson().getText();
        BackAndForth backAndForth = BackAndForth.JSON_READER.read(jsonIn);
        backAndForth.name = NameConverter.GERMAN;
        String jsonOut = BackAndForth.JSON_WRITER.toJson(backAndForth);
        JSONObject jsonObject = JSONParser.parseStrict(jsonOut).isObject();
        assertNotNull(jsonObject);
        // TODO More asserts
    }
}
