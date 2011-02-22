package name.pehl.piriti.client.gwttest.constraints;

import java.util.ArrayList;
import java.util.List;

/**
 * Among normal {@link Amoeba} JSON tests, this class contains methods to test
 * writing <code>null</code>, empty, blank and invalid values.
 * 
 * @author $Author$
 * @version $Revision$
 */
public class JsonAmoebaWriterTest extends AbstractAmoebaTest
{
    public static final String NULL_STRING = null;


    // ----------------------------------------------------- write single tests

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


    public void testWriteAmoeba()
    {
        Amoeba alpha = new Amoeba();
        alpha.setName("Alpha");
        alpha.setSize(1);
        String json = Amoeba.JSON_WRITER.toJson(alpha);
        assertEquals("{\"name\":\"Alpha\",\"__size__\":1}", json);
    }


    public void testWriteAmoebaWithNullName()
    {
        Amoeba alpha = new Amoeba();
        alpha.setName(null);
        String json = Amoeba.JSON_WRITER.toJson(alpha);
        assertEquals("{\"name\":null,\"__size__\":0}", json);
    }


    // ------------------------------------------------------- write list tests

    public void testWriteNullAmoebas()
    {
        String json = null;

        json = Amoeba.JSON_WRITER.toJson(null, NULL_STRING);
        assertNull(json);
        json = Amoeba.JSON_WRITER.toJson(null, "amoebas");
        assertNull(json);
    }


    public void testWriteEmptyAmoebas()
    {
        String json = null;
        List<Amoeba> amoebas = new ArrayList<Amoeba>();

        json = Amoeba.JSON_WRITER.toJson(amoebas, NULL_STRING);
        assertNull(json);
        json = Amoeba.JSON_WRITER.toJson(amoebas, "amoebas");
        assertEquals("{\"amoebas\":[]}", json);
    }


    public void testWriteAmoebas()
    {
        String json = null;
        List<Amoeba> amoebas = new ArrayList<Amoeba>();
        Amoeba a = new Amoeba();
        a.setName("Alpha");
        a.setSize(1);
        amoebas.add(a);
        Amoeba b = new Amoeba();
        b.setName("Bravo");
        b.setSize(1);
        amoebas.add(b);
        Amoeba c = new Amoeba();
        c.setName("Charlie");
        c.setSize(1);
        amoebas.add(c);

        json = Amoeba.JSON_WRITER.toJson(amoebas, NULL_STRING);
        assertNull(json);
        json = Amoeba.JSON_WRITER.toJson(amoebas, "amoebas");
        assertEquals(
                "{\"amoebas\":[{\"name\":\"Alpha\",\"__size__\":1},{\"name\":\"Bravo\",\"__size__\":1},{\"name\":\"Charlie\",\"__size__\":1}]}",
                json);
    }
}
