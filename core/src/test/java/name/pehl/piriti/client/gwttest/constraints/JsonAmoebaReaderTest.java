package name.pehl.piriti.client.gwttest.constraints;

import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

/**
 * Among normal {@link Amoeba} JSON tests, this class contains methods to test
 * reading <code>null</code>, empty, blank and invalid values.
 * 
 * @author $Author: harald.pehl $
 * @version $Revision: 131 $
 */
public class JsonAmoebaReaderTest extends AbstractAmoebaTest
{
    public static final String NULL_STRING = null;
    public static final String EMPTY_STRING = "";
    public static final String BLANK_STRING = "          ";
    public static final String INVALID_JSON_STRING = "________invalid________";
    public static final String EMPTY_JSON_STRING = "{}";

    public static final JSONObject NULL_JSON_OBJECT = null;


    // ------------------------------------------------ read single from string

    public void testReadSingleFromNullString()
    {
        Amoeba amoeba = Amoeba.JSON_READER.read(NULL_STRING);
        assertNull(amoeba);
    }


    public void testReadSingleFromEmptyString()
    {
        Amoeba amoeba = Amoeba.JSON_READER.read(EMPTY_STRING);
        assertNull(amoeba);
    }


    public void testReadSingleFromBlankString()
    {
        Amoeba amoeba = Amoeba.JSON_READER.read(BLANK_STRING);
        assertNull(amoeba);
    }


    public void testReadSingleFromInvalidString()
    {
        try
        {
            Amoeba.JSON_READER.read(INVALID_JSON_STRING);
            fail(JSONException.class.getName() + " expected!");
        }
        catch (JSONException e)
        {
        }
    }


    public void testReadSingleFromEmptyJsonString()
    {
        Amoeba amoeba = Amoeba.JSON_READER.read(EMPTY_JSON_STRING);
        assertNotNull(amoeba);
        assertEquals(AmoebaResources.BLUEPRINT_AMOEBA, amoeba);
    }


    public void testReadSingleFromString()
    {
        String json = AmoebaResources.INSTANCE.amoebaJson().getText();
        Amoeba amoeba = Amoeba.JSON_READER.read(json);
        assertAmoeba(amoeba, AmoebaResources.ALPHA);
    }


    // -------------------------------------------- read single from JSONObject

    public void testReadSingleFromNullJSONObject()
    {
        Amoeba amoeba = Amoeba.JSON_READER.read(NULL_JSON_OBJECT);
        assertNull(amoeba);
    }


    public void testReadSingleFromEmptyJSONObject()
    {
        Amoeba amoeba = Amoeba.JSON_READER.read(new JSONObject());
        assertNotNull(amoeba);
        assertEquals(AmoebaResources.BLUEPRINT_AMOEBA, amoeba);
    }


    public void testReadSingleFromJSONObject()
    {
        JSONObject json = new JSONObject();
        json.put("name", new JSONString(AmoebaResources.ALPHA));
        json.put("__size__", new JSONNumber(1));
        Amoeba amoeba = Amoeba.JSON_READER.read(json);
        assertAmoeba(amoeba, AmoebaResources.ALPHA);
    }


    // -------------------------------------------------- read list from string

    public void testReadListFromNullString()
    {
        List<Amoeba> amoebas = Amoeba.JSON_READER.readList(NULL_STRING);
        assertNull(amoebas);
    }


    public void testReadListFromEmptyString()
    {
        List<Amoeba> amoebas = Amoeba.JSON_READER.readList(EMPTY_STRING);
        assertNull(amoebas);
    }


    public void testReadListFromBlankString()
    {
        List<Amoeba> amoebas = Amoeba.JSON_READER.readList(BLANK_STRING);
        assertNull(amoebas);
    }


    public void testReadListFromInvalidString()
    {
        try
        {
            Amoeba.JSON_READER.readList(INVALID_JSON_STRING);
            fail(JSONException.class.getName() + " expected!");
        }
        catch (JSONException e)
        {
        }
    }


    public void testReadListFromEmptyJsonString()
    {
        List<Amoeba> amoebas = Amoeba.JSON_READER.readList(EMPTY_JSON_STRING);
        assertNull(amoebas);
    }


    public void testReadListFromStringWithNonExistingArray()
    {
        List<Amoeba> amoebas = null;

        amoebas = Amoeba.JSON_READER.readList("{\"foo\": null}");
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList("{\"foo\": 123}");
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList("{\"foo\": true}");
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList("{\"foo\": \"\"}");
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList("{\"foo\": {}}");
        assertNull(amoebas);
    }


    public void testReadListFromStringWithEmptyArray()
    {
        List<Amoeba> amoebas = Amoeba.JSON_READER.readList("{\"foo\":[]}");
        assertNotNull(amoebas);
        assertTrue(amoebas.isEmpty());
    }


    public void testReadListFromString()
    {
        String json = AmoebaResources.INSTANCE.amoebasJson().getText();
        List<Amoeba> amoebas = Amoeba.JSON_READER.readList(json);
        assertAmoebas(amoebas);
    }


    // ----------------------------------- read list from string with array key

    public void testReadListFromNullStringWithArrayKey()
    {
        List<Amoeba> amoebas = null;

        amoebas = Amoeba.JSON_READER.readList(NULL_STRING, NULL_STRING);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(NULL_STRING, EMPTY_STRING);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(NULL_STRING, BLANK_STRING);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(NULL_STRING, INVALID_JSON_STRING);
        assertNull(amoebas);
    }


    public void testReadListFromEmptyStringWithArrayKey()
    {
        List<Amoeba> amoebas = null;

        amoebas = Amoeba.JSON_READER.readList(EMPTY_STRING, NULL_STRING);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(EMPTY_STRING, EMPTY_STRING);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(EMPTY_STRING, BLANK_STRING);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(EMPTY_STRING, INVALID_JSON_STRING);
        assertNull(amoebas);
    }


    public void testReadListFromBlankStringWithArrayKey()
    {
        List<Amoeba> amoebas = null;

        amoebas = Amoeba.JSON_READER.readList(BLANK_STRING, NULL_STRING);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(BLANK_STRING, EMPTY_STRING);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(BLANK_STRING, BLANK_STRING);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(BLANK_STRING, INVALID_JSON_STRING);
        assertNull(amoebas);
    }


    public void testReadListFromInvalidStringWithArrayKey()
    {
        try
        {
            Amoeba.JSON_READER.readList(INVALID_JSON_STRING, NULL_STRING);
            fail(JSONException.class.getName() + " expected!");
        }
        catch (JSONException e)
        {
        }
        try
        {
            Amoeba.JSON_READER.readList(INVALID_JSON_STRING, EMPTY_STRING);
            fail(JSONException.class.getName() + " expected!");
        }
        catch (JSONException e)
        {
        }
        try
        {
            Amoeba.JSON_READER.readList(INVALID_JSON_STRING, BLANK_STRING);
            fail(JSONException.class.getName() + " expected!");
        }
        catch (JSONException e)
        {
        }
        try
        {
            Amoeba.JSON_READER.readList(INVALID_JSON_STRING, INVALID_JSON_STRING);
            fail(JSONException.class.getName() + " expected!");
        }
        catch (JSONException e)
        {
        }
    }


    public void testReadListFromEmptyJsonStringWithArrayKey()
    {
        List<Amoeba> amoebas = null;

        amoebas = Amoeba.JSON_READER.readList(EMPTY_JSON_STRING, NULL_STRING);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(EMPTY_JSON_STRING, EMPTY_STRING);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(EMPTY_JSON_STRING, BLANK_STRING);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(EMPTY_JSON_STRING, INVALID_JSON_STRING);
        assertNull(amoebas);
    }


    public void testReadListFromStringWithNonExistingArrayKey()
    {
        List<Amoeba> amoebas = Amoeba.JSON_READER.readList("{\"foo\": []}", "bar");
        assertNull(amoebas);
    }


    public void testReadListFromStringWithInvalidArrayKey()
    {
        List<Amoeba> amoebas = null;

        amoebas = Amoeba.JSON_READER.readList("{\"foo\": null}", "foo");
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList("{\"foo\": 123}", "foo");
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList("{\"foo\": true}", "foo");
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList("{\"foo\": \"\"}", "foo");
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList("{\"foo\": {}}", "foo");
        assertNull(amoebas);
    }


    public void testReadListFromStringWithEmptyArrayKey()
    {
        List<Amoeba> amoebas = Amoeba.JSON_READER.readList("{\"foo\": []}", "foo");
        assertNotNull(amoebas);
        assertTrue(amoebas.isEmpty());
    }


    public void testReadListFromStringWithArrayKey()
    {
        String json = AmoebaResources.INSTANCE.amoebasJson().getText();
        List<Amoeba> amoebas = Amoeba.JSON_READER.readList(json, "amoebas");
        assertAmoebas(amoebas);
    }


    // ---------------------------------------------- read list from JSONObject

    public void testReadListFromNullJSONObject()
    {
        List<Amoeba> amoebas = Amoeba.JSON_READER.readList(NULL_JSON_OBJECT);
        assertNull(amoebas);
    }


    public void testReadListFromEmptyJSONObject()
    {
        List<Amoeba> amoebas = Amoeba.JSON_READER.readList(new JSONObject());
        assertNull(amoebas);
    }


    public void testReadListFromJSONObjectWithNonExistingArray()
    {
        JSONObject json = null;
        List<Amoeba> amoebas = null;

        json = new JSONObject();
        json.put("foo", JSONNull.getInstance());
        amoebas = Amoeba.JSON_READER.readList(json);
        assertNull(amoebas);

        json = new JSONObject();
        json.put("foo", new JSONNumber(123));
        amoebas = Amoeba.JSON_READER.readList(json);
        assertNull(amoebas);

        json = new JSONObject();
        json.put("foo", JSONBoolean.getInstance(false));
        amoebas = Amoeba.JSON_READER.readList(json);
        assertNull(amoebas);

        json = new JSONObject();
        json.put("foo", new JSONObject());
        amoebas = Amoeba.JSON_READER.readList(json);
        assertNull(amoebas);
    }


    public void testReadListFromJSONObjectWithEmptyArray()
    {
        JSONObject json = new JSONObject();
        json.put("foo", new JSONArray());
        List<Amoeba> amoebas = Amoeba.JSON_READER.readList(json);
        assertNotNull(amoebas);
        assertTrue(amoebas.isEmpty());
    }


    public void testReadListFromJSONObject()
    {
        JSONObject a = new JSONObject();
        a.put("name", new JSONString("Alpha"));
        a.put("__size__", new JSONNumber(1));
        JSONObject b = new JSONObject();
        b.put("name", new JSONString("Bravo"));
        b.put("__size__", new JSONNumber(1));
        JSONObject c = new JSONObject();
        c.put("name", new JSONString("Charlie"));
        c.put("__size__", new JSONNumber(1));
        JSONArray array = new JSONArray();
        array.set(0, a);
        array.set(1, b);
        array.set(2, c);
        JSONObject json = new JSONObject();
        json.put("amoebas", array);
        List<Amoeba> amoebas = Amoeba.JSON_READER.readList(json);
        assertAmoebas(amoebas);
    }


    // ------------------------------- read list from JSONObject with array key

    public void testReadListFromNullJSONObjectWithArrayKey()
    {
        List<Amoeba> amoebas = null;

        amoebas = Amoeba.JSON_READER.readList(NULL_JSON_OBJECT, NULL_STRING);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(NULL_JSON_OBJECT, EMPTY_STRING);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(NULL_JSON_OBJECT, BLANK_STRING);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(NULL_JSON_OBJECT, INVALID_JSON_STRING);
        assertNull(amoebas);
    }


    public void testReadListFromEmptyJSONObjectWithArrayKey()
    {
        List<Amoeba> amoebas = null;

        amoebas = Amoeba.JSON_READER.readList(new JSONObject(), NULL_STRING);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(new JSONObject(), EMPTY_STRING);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(new JSONObject(), BLANK_STRING);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(new JSONObject(), INVALID_JSON_STRING);
        assertNull(amoebas);
    }


    public void testReadListFromJSONObjectWithNonExistingArrayKey()
    {
        JSONObject json = new JSONObject();
        json.put("foo", new JSONArray());
        List<Amoeba> amoebas = Amoeba.JSON_READER.readList(json, "bar");
        assertNull(amoebas);
    }


    public void testReadListFromJSONObjectWithInvalidArrayKey()
    {
        JSONObject json = null;
        List<Amoeba> amoebas = null;

        json = new JSONObject();
        json.put("foo", JSONNull.getInstance());
        amoebas = Amoeba.JSON_READER.readList(json, "foo");
        assertNull(amoebas);

        json = new JSONObject();
        json.put("foo", new JSONNumber(123));
        amoebas = Amoeba.JSON_READER.readList(json, "foo");
        assertNull(amoebas);

        json = new JSONObject();
        json.put("foo", JSONBoolean.getInstance(false));
        amoebas = Amoeba.JSON_READER.readList(json, "foo");
        assertNull(amoebas);

        json = new JSONObject();
        json.put("foo", new JSONObject());
        amoebas = Amoeba.JSON_READER.readList(json, "foo");
        assertNull(amoebas);
    }


    public void testReadListFromJSONObjectWithEmptyArrayWithArrayKey()
    {
        JSONObject json = new JSONObject();
        json.put("foo", new JSONArray());
        List<Amoeba> amoebas = Amoeba.JSON_READER.readList(json, "foo");
        assertNotNull(amoebas);
        assertTrue(amoebas.isEmpty());
    }


    public void testReadListFromJSONObjectWithArrayKey()
    {
        JSONObject a = new JSONObject();
        a.put("name", new JSONString("Alpha"));
        a.put("__size__", new JSONNumber(1));
        JSONObject b = new JSONObject();
        b.put("name", new JSONString("Bravo"));
        b.put("__size__", new JSONNumber(1));
        JSONObject c = new JSONObject();
        c.put("name", new JSONString("Charlie"));
        c.put("__size__", new JSONNumber(1));
        JSONArray array = new JSONArray();
        array.set(0, a);
        array.set(1, b);
        array.set(2, c);
        JSONObject json = new JSONObject();
        json.put("amoebas", array);
        List<Amoeba> amoebas = Amoeba.JSON_READER.readList(json, "amoebas");
        assertAmoebas(amoebas);
    }


    // ------------------------------------------------------------ write tests

    public void testWriteNullAmoeba()
    {
        String json = Amoeba.JSON_WRITER.toJson(null);
        assertNull(json);
    }


    public void testWriteEmptyAmoeba()
    {
        String json = Amoeba.JSON_WRITER.toJson(new Amoeba());
        assertEquals("{\"name\":\"blueprint\",\"__size__\":0}", json);
    }
}
