package name.pehl.piriti.client.gwttest.simple;

import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONObject;

/**
 * This class contains methods to test reading and writing <code>null</code> and
 * empty values. 
 * 
 * @author $Author: harald.pehl $
 * @version $Revision: 131 $
 */
public class JsonAmoebaTest extends AbstractAmoebaTest
{
    private static final String SPACES = "          ";
    private static final String INVALID = "________invalid________";


    // ------------------------------------------------------------ read single

    public void testReadNull()
    {
        Amoeba amoeba = Amoeba.JSON_READER.read((String) null);
        assertNull(amoeba);
        amoeba = Amoeba.JSON_READER.read((JSONObject) null);
        assertNull(amoeba);
    }


    public void testReadEmpty()
    {
        Amoeba amoeba = Amoeba.JSON_READER.read("");
        assertNull(amoeba);
        amoeba = Amoeba.JSON_READER.read(SPACES);
        assertNull(amoeba);
        amoeba = Amoeba.JSON_READER.read(new JSONObject());
        assertNotNull(amoeba);
        assertEquals(AmoebaResources.BLUEPRINT_AMOEBA, amoeba);
    }


    public void testReadInvalid()
    {
        try
        {
            Amoeba.JSON_READER.read(INVALID);
            fail(JSONException.class.getName() + " expected!");
        }
        catch (JSONException e)
        {
        }
    }


    public void testRead()
    {
        String json = AmoebaResources.INSTANCE.amoebaJson().getText();
        Amoeba amoeba = Amoeba.JSON_READER.read(json);
        assertAmoeba(amoeba, AmoebaResources.ALPHA);
    }


    // -------------------------------------------------------------- read list

    public void testReadListNull()
    {
        List<Amoeba> amoebas = null;

        // read from String
        amoebas = Amoeba.JSON_READER.readList((String) null);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList((String) null, null);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList((String) null, "");
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList((String) null, SPACES);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList((String) null, INVALID);
        assertNull(amoebas);

        // read from JSONObject
        amoebas = Amoeba.JSON_READER.readList((JSONObject) null);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList((JSONObject) null, null);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList((JSONObject) null, "");
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList((JSONObject) null, SPACES);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList((JSONObject) null, INVALID);
        assertNull(amoebas);

        // read from JSONArray
        amoebas = Amoeba.JSON_READER.readList((JSONArray) null);
        assertNull(amoebas);
    }


    public void testReadListEmptyString()
    {
        List<Amoeba> amoebas = null;

        amoebas = Amoeba.JSON_READER.readList("");
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList("", null);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList("", "");
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList("", SPACES);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList("", INVALID);
        assertNull(amoebas);
    }


    public void testReadListBlankString()
    {
        List<Amoeba> amoebas = null;

        amoebas = Amoeba.JSON_READER.readList(SPACES);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(SPACES, null);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(SPACES, "");
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(SPACES, SPACES);
        assertNull(amoebas);
        amoebas = Amoeba.JSON_READER.readList(SPACES, INVALID);
        assertNull(amoebas);
    }


    public void testReadListInvalidString()
    {
        try
        {
            Amoeba.JSON_READER.readList(INVALID);
            fail(JSONException.class.getName() + " expected!");
        }
        catch (JSONException e)
        {
        }
        try
        {
            Amoeba.JSON_READER.readList(INVALID, null);
            fail(JSONException.class.getName() + " expected!");
        }
        catch (JSONException e)
        {
        }
        try
        {
            Amoeba.JSON_READER.readList(INVALID, "");
            fail(JSONException.class.getName() + " expected!");
        }
        catch (JSONException e)
        {
        }
        try
        {
            Amoeba.JSON_READER.readList(INVALID, SPACES);
            fail(JSONException.class.getName() + " expected!");
        }
        catch (JSONException e)
        {
        }
        try
        {
            Amoeba.JSON_READER.readList(INVALID, INVALID);
            fail(JSONException.class.getName() + " expected!");
        }
        catch (JSONException e)
        {
        }
    }


    public void testReadListJSONObject()
    {
        // TODO
    }


    public void testReadListJSONArray()
    {
        // TODO
    }


    public void testReadList()
    {
        String json = AmoebaResources.INSTANCE.amoebasJson().getText();
        List<Amoeba> amoebas = Amoeba.JSON_READER.readList(json);
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
        assertEquals("{\"name\":\"blueprint\"}", json);
    }
}
