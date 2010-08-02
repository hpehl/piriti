package name.pehl.piriti.client.gwttest.simple;

import java.util.List;

import name.pehl.totoe.client.Document;
import name.pehl.totoe.client.Element;
import name.pehl.totoe.client.XmlParseException;

/**
 * Among normal {@link Amoeba} JSON tests, this class contains methods to test
 * reading <code>null</code>, empty, blank and invalid values.
 * 
 * @author $Author$
 * @version $Revision$
 */
public class XmlAmoebaReaderTest extends AbstractAmoebaTest
{
    public static final String NULL_STRING = null;
    public static final String EMPTY_STRING = "";
    public static final String BLANK_STRING = "          ";
    public static final String INVALID_XML_STRING = "________invalid________";

    public static final String EMPTY_SINGLE_XML_STRING = "<amoeba/>";
    public static final String EMPTY_LIST_XML_STRING = "<amoebas/>";


    // ------------------------------------------------ read single from string

    public void testReadSingleFromNullString()
    {
        Amoeba amoeba = Amoeba.XML_READER.read(NULL_STRING);
        assertNull(amoeba);
    }


    public void testReadSingleFromEmptyString()
    {
        Amoeba amoeba = Amoeba.XML_READER.read(EMPTY_STRING);
        assertNull(amoeba);
    }


    public void testReadSingleFromBlankString()
    {
        Amoeba amoeba = Amoeba.XML_READER.read(BLANK_STRING);
        assertNull(amoeba);
    }


    public void testReadSingleFromInvalidString()
    {
        try
        {
            Amoeba.XML_READER.read(INVALID_XML_STRING);
            fail(XmlParseException.class.getName() + " expected!");
        }
        catch (XmlParseException e)
        {
        }
    }


    public void testReadSingleFromEmptyXmlString()
    {
        Amoeba amoeba = Amoeba.XML_READER.read(EMPTY_SINGLE_XML_STRING);
        assertNotNull(amoeba);
        assertEquals(AmoebaResources.BLUEPRINT_AMOEBA, amoeba);
    }


    public void testReadSingleFromString()
    {
        String xml = AmoebaResources.INSTANCE.amoebaXml().getText();
        Amoeba amoeba = Amoeba.XML_READER.read(xml);
        assertAmoeba(amoeba, AmoebaResources.ALPHA);
    }


    // ---------------------------------------------- read single from document

    public void testReadSingleFromNullDocument()
    {
        Amoeba amoeba = Amoeba.XML_READER.read((Document) null);
        assertNull(amoeba);
    }


    // TODO Read from empty and valid document

    // ----------------------------------------------- read single from element

    public void testReadSingleFromNullElement()
    {
        Amoeba amoeba = Amoeba.XML_READER.read((Element) null);
        assertNull(amoeba);
    }


    // TODO Read from empty and valid element

    // -------------------------------------------------- read list from string

    public void testReadListFromNullString()
    {
        List<Amoeba> amoebas = Amoeba.XML_READER.readList(NULL_STRING);
        assertNull(amoebas);
    }


    public void testReadListFromEmptyString()
    {
        List<Amoeba> amoebas = Amoeba.XML_READER.readList(EMPTY_STRING);
        assertNull(amoebas);
    }


    public void testReadListFromBlankString()
    {
        List<Amoeba> amoebas = Amoeba.XML_READER.readList(BLANK_STRING);
        assertNull(amoebas);
    }


    public void testReadListFromInvalidString()
    {
        try
        {
            Amoeba.XML_READER.readList(INVALID_XML_STRING);
            fail(XmlParseException.class.getName() + " expected!");
        }
        catch (XmlParseException e)
        {
        }
    }


    public void testReadListFromEmptyXmlString()
    {
        List<Amoeba> amoebas = Amoeba.XML_READER.readList(EMPTY_LIST_XML_STRING);
        assertNull(amoebas);
    }


    public void testReadListFromString()
    {
        String xml = AmoebaResources.INSTANCE.amoebasXml().getText();
        List<Amoeba> amoebas = Amoeba.XML_READER.readList(xml);
        assertAmoebas(amoebas);
    }

    // ------------------------------------------------ read list from document

    // NYI

    // ------------------------------------------------- read list from element

    // NYI
}
