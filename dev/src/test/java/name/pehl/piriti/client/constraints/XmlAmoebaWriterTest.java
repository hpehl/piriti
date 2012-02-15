package name.pehl.piriti.client.constraints;

import java.util.ArrayList;
import java.util.List;

/**
 * Among normal {@link Amoeba} JSON tests, this class contains methods to test
 * writing <code>null</code>, empty, blank and invalid values.
 * 
 * @author $Author: harald.pehl $
 * @version $Revision: 1478 $
 */
public class XmlAmoebaWriterTest extends AbstractAmoebaTest
{
    public static final String NULL_STRING = null;
    public static final String PROLOG = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";


    // ----------------------------------------------------- write single tests

    public void testWriteNullAmoeba()
    {
        String xml = Amoeba.XML_WRITER.toXml((Amoeba) null);
        assertNull(xml);
    }


    public void testWriteEmptyAmoeba()
    {
        String xml = Amoeba.XML_WRITER.toXml(new Amoeba());
        assertEquals(PROLOG + "<amoeba><name>blueprint</name><__size__>0</__size__></amoeba>", xml);
    }


    public void testWriteAmoeba()
    {
        Amoeba alpha = new Amoeba();
        alpha.setName("Alpha");
        alpha.setSize(1);
        String xml = Amoeba.XML_WRITER.toXml(alpha);
        assertEquals(PROLOG + "<amoeba><name>Alpha</name><__size__>1</__size__></amoeba>", xml);
    }


    public void testWriteAmoebaWithNullName()
    {
        Amoeba alpha = new Amoeba();
        alpha.setName(null);
        String xml = Amoeba.XML_WRITER.toXml(alpha);
        assertEquals(PROLOG + "<amoeba><name/><__size__>0</__size__></amoeba>", xml);
    }


    // ------------------------------------------------------- write list tests

    public void testWriteNullAmoebas()
    {
        String xml = null;

        xml = Amoeba.XML_WRITER.toXml((List<Amoeba>) null, NULL_STRING);
        assertNull(xml);
        xml = Amoeba.XML_WRITER.toXml((List<Amoeba>) null, "amoebas");
        assertNull(xml);
    }


    public void testWriteEmptyAmoebas()
    {
        String xml = null;
        List<Amoeba> amoebas = new ArrayList<Amoeba>();

        xml = Amoeba.XML_WRITER.toXml(amoebas, NULL_STRING);
        assertNull(xml);
        xml = Amoeba.XML_WRITER.toXml(amoebas, "amoebas");
        assertEquals(PROLOG + "<amoebas/>", xml);
    }


    public void testWriteAmoebas()
    {
        String xml = null;
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

        xml = Amoeba.XML_WRITER.toXml(amoebas, NULL_STRING);
        assertNull(xml);
        xml = Amoeba.XML_WRITER.toXml(amoebas, "amoebas");
        assertEquals(
                PROLOG
                        + "<amoebas><amoeba><name>Alpha</name><__size__>1</__size__></amoeba><amoeba><name>Bravo</name><__size__>1</__size__></amoeba><amoeba><name>Charlie</name><__size__>1</__size__></amoeba></amoebas>",
                xml);
    }
}
