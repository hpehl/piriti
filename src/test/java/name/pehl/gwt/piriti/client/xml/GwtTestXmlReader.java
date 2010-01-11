package name.pehl.gwt.piriti.client.xml;

import java.util.Date;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.xml.client.Document;

/**
 * @author $Author$
 * @version $Revision$
 */
public class GwtTestXmlReader extends GWTTestCase
{
    private static final Date MY_BIRTHDAY = new Date(115813353000l);


    @Override
    public String getModuleName()
    {
        return "name.pehl.gwt.piriti.PiritiTest";
    }


    @Override
    protected void gwtSetUp() throws Exception
    {
        // To register the XmlReaders
        new DemoNestedModel();
        new DemoModel();
    }


    public void testReadSingle()
    {
        Document document = DemoXmlFactory.createDemoModelDocument("demoModel");
        DemoModel demo = DemoModel.XML.readSingle(document);

        assertNotNull(demo);
        assertTrue(demo.booleanPrimitive);
        assertTrue(demo.booleanObject);
        assertEquals(1, demo.bytePrimitive);
        assertEquals(2, demo.byteObject.byteValue());
        assertEquals('a', demo.characterPrimitive);
        assertEquals('b', demo.characterObject.charValue());
        assertEquals(MY_BIRTHDAY, demo.date);
        assertEquals(DemoEnum.DREI, demo.demoEnum);
        assertEquals(4.5, demo.doublePrimitive);
        assertEquals(6.7, demo.doubleObject.doubleValue());
        assertEquals(8.9f, demo.floatPrimitive);
        assertEquals(10.11f, demo.floatObject.floatValue());
        assertEquals(12, demo.integerPrimitive);
        assertEquals(13, demo.integerObject.intValue());
        assertEquals(14l, demo.longPrimitive);
        assertEquals(15l, demo.longObject.longValue());
        assertEquals(16, demo.shortPrimitive);
        assertEquals(17, demo.shortObject.shortValue());
        assertEquals("achtzehn", demo.string);
        assertEquals("neunzehn", demo.stringAttribute);
        assertNull(demo.listOfDemoModels);
        assertNull(demo.untypedList);
        assertNull(demo.mapOfDemoModels);
        assertNull(demo.untypedMap);

        assertNotNull(demo.demoNestedModel);
        assertTrue(demo.demoNestedModel.booleanPrimitive);
        assertTrue(demo.demoNestedModel.booleanObject);
        assertEquals(1, demo.demoNestedModel.bytePrimitive);
        assertEquals(2, demo.demoNestedModel.byteObject.byteValue());
        assertEquals('a', demo.demoNestedModel.characterPrimitive);
        assertEquals('b', demo.demoNestedModel.characterObject.charValue());
        assertEquals(MY_BIRTHDAY, demo.demoNestedModel.date);
        assertEquals(DemoEnum.DREI, demo.demoNestedModel.demoEnum);
        assertEquals(4.5, demo.demoNestedModel.doublePrimitive);
        assertEquals(6.7, demo.demoNestedModel.doubleObject.doubleValue());
        assertEquals(8.9f, demo.demoNestedModel.floatPrimitive);
        assertEquals(10.11f, demo.demoNestedModel.floatObject.floatValue());
        assertEquals(12, demo.demoNestedModel.integerPrimitive);
        assertEquals(13, demo.demoNestedModel.integerObject.intValue());
        assertEquals(14l, demo.demoNestedModel.longPrimitive);
        assertEquals(15l, demo.demoNestedModel.longObject.longValue());
        assertEquals(16, demo.demoNestedModel.shortPrimitive);
        assertEquals(17, demo.demoNestedModel.shortObject.shortValue());
        assertEquals("achtzehn", demo.demoNestedModel.string);
        assertEquals("neunzehn", demo.demoNestedModel.stringAttribute);
    }
}
