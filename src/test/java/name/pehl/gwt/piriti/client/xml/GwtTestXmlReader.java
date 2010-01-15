package name.pehl.gwt.piriti.client.xml;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.xml.client.Document;

/**
 * @author $Author$
 * @version $Revision$
 */
public class GwtTestXmlReader extends GWTTestCase
{
    private static final Date MY_BIRTHDAY = new Date(115813353000l);

    private Set<Integer> setOfIntegerObjectsFixture;
    private Set<String> setOfStringsFixture;


    @Override
    public String getModuleName()
    {
        return "name.pehl.gwt.piriti.PiritiTest";
    }


    @Override
    protected void gwtSetUp() throws Exception
    {
        // Register the XmlReaders
        new DemoNestedModel();
        new DemoModel();

        // Setup collection fixtures
        setOfIntegerObjectsFixture = new HashSet<Integer>();
        setOfIntegerObjectsFixture.add(0);
        setOfIntegerObjectsFixture.add(1);
        setOfIntegerObjectsFixture.add(2);
        setOfStringsFixture = new HashSet<String>();
        setOfStringsFixture.add("0");
        setOfStringsFixture.add("1");
        setOfStringsFixture.add("2");
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

        // Nested objects
        assertNull(demo.demoModel);
        assertDemoNestedModel(demo.demoNestedModel);

        // Arrays
        assertEquals(3, demo.arrayOfIntegerPrimitives.length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(i, demo.arrayOfIntegerPrimitives[i]);
        }
        assertEquals(3, demo.arrayOfIntegerObjects.length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(i, demo.arrayOfIntegerObjects[i].intValue());
        }
        assertEquals(3, demo.arrayOfStrings.length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(String.valueOf(i), demo.arrayOfStrings[i]);
        }
        assertEquals(3, demo.arrayOfDemoNestedModels.length);
        for (int i = 0; i < 3; i++)
        {
            assertDemoNestedModel(demo.arrayOfDemoNestedModels[i]);
        }

        // Invalid arrays
        assertNull(demo.arrayOfDemoModels);
        assertNull(demo.arrayOfLists);
        assertNull(demo.arrayOfMaps);
        assertNull(demo.multiDimensionalIntegerPrimitiveArray);
        assertNull(demo.multiDimensionalIntegerObjectArray);

        // Collections
        assertEquals(3, demo.collectionOfIntegerObjects.size());
        demo.collectionOfIntegerObjects.removeAll(setOfIntegerObjectsFixture);
        assertTrue(demo.collectionOfIntegerObjects.isEmpty());
        assertEquals(3, demo.collectionOfStrings.size());
        demo.collectionOfStrings.removeAll(setOfStringsFixture);
        assertTrue(demo.collectionOfStrings.isEmpty());
        assertEquals(3, demo.collectionOfDemoNestedModels.size());
        for (DemoNestedModel dnm : demo.collectionOfDemoNestedModels)
        {
            assertDemoNestedModel(dnm);
        }

        // Invalid collections
        assertNull(demo.untypedCollection);
        assertNull(demo.collectionOfDemoModels);
        assertNull(demo.collectionOfArrays);
        assertNull(demo.collectionOfCollections);
        assertNull(demo.collectionOfMaps);

        // Lists
        assertEquals(3, demo.listOfIntegerObjects.size());
        for (int i = 0; i < 3; i++)
        {
            assertEquals(i, demo.listOfIntegerObjects.get(i).intValue());
        }
        assertEquals(3, demo.listOfStrings.size());
        for (int i = 0; i < 3; i++)
        {
            assertEquals(String.valueOf(i), demo.listOfStrings.get(i));
        }
        assertEquals(3, demo.listOfDemoNestedModels.size());
        for (int i = 0; i < 3; i++)
        {
            assertDemoNestedModel(demo.listOfDemoNestedModels.get(i));
        }

        // Invalid lists
        assertNull(demo.untypedList);
        assertNull(demo.listOfDemoModels);
        assertNull(demo.listOfArrays);
        assertNull(demo.listOfLists);
        assertNull(demo.listOfMaps);

        // Sets
        assertEquals(3, demo.setOfIntegerObjects.size());
        demo.setOfIntegerObjects.removeAll(setOfIntegerObjectsFixture);
        assertTrue(demo.setOfIntegerObjects.isEmpty());
        assertEquals(3, demo.setOfStrings.size());
        demo.setOfStrings.removeAll(setOfStringsFixture);
        assertTrue(demo.setOfStrings.isEmpty());
        assertEquals(3, demo.setOfDemoNestedModels.size());
        for (DemoNestedModel dnm : demo.setOfDemoNestedModels)
        {
            assertDemoNestedModel(dnm);
        }

        // Invalid sets
        assertNull(demo.untypedSet);
        assertNull(demo.setOfDemoModels);
        assertNull(demo.setOfArrays);
        assertNull(demo.setOfSets);
        assertNull(demo.setOfMaps);
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
