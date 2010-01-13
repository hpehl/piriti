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

        // Primitives and simple objects
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

        // Nested objects
        assertDemoNestedModel(demo.demoNestedModel);

        // Arrays
        assertNotNull(demo.arrayOfIntegerPrimitives);
        assertEquals(3, demo.arrayOfIntegerPrimitives.length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(i, demo.arrayOfIntegerPrimitives[i]);
        }
        assertNotNull(demo.arrayOfIntegerObjects);
        assertEquals(3, demo.arrayOfIntegerObjects.length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(i, demo.arrayOfIntegerObjects[i].intValue());
        }
        assertNotNull(demo.arrayOfStrings);
        assertEquals(3, demo.arrayOfStrings.length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(String.valueOf(i), demo.arrayOfStrings[i]);
        }
        assertNotNull(demo.arrayOfDemoNestedModels);
        assertEquals(3, demo.arrayOfDemoNestedModels.length);
        for (int i = 0; i < 3; i++)
        {
            assertDemoNestedModel(demo.arrayOfDemoNestedModels[i]);
        }
    }


    private void assertDemoNestedModel(DemoNestedModel demoNestedModel)
    {
        assertNotNull(demoNestedModel);
        assertTrue(demoNestedModel.booleanPrimitive);
        assertTrue(demoNestedModel.booleanObject);
        assertEquals(1, demoNestedModel.bytePrimitive);
        assertEquals(2, demoNestedModel.byteObject.byteValue());
        assertEquals('a', demoNestedModel.characterPrimitive);
        assertEquals('b', demoNestedModel.characterObject.charValue());
        assertEquals(MY_BIRTHDAY, demoNestedModel.date);
        assertEquals(DemoEnum.DREI, demoNestedModel.demoEnum);
        assertEquals(4.5, demoNestedModel.doublePrimitive);
        assertEquals(6.7, demoNestedModel.doubleObject.doubleValue());
        assertEquals(8.9f, demoNestedModel.floatPrimitive);
        assertEquals(10.11f, demoNestedModel.floatObject.floatValue());
        assertEquals(12, demoNestedModel.integerPrimitive);
        assertEquals(13, demoNestedModel.integerObject.intValue());
        assertEquals(14l, demoNestedModel.longPrimitive);
        assertEquals(15l, demoNestedModel.longObject.longValue());
        assertEquals(16, demoNestedModel.shortPrimitive);
        assertEquals(17, demoNestedModel.shortObject.shortValue());
        assertEquals("achtzehn", demoNestedModel.string);
        assertEquals("neunzehn", demoNestedModel.stringAttribute);
    }
}
