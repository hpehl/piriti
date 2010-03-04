package name.pehl.gwt.piriti.client.model.xml;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import name.pehl.gwt.piriti.client.model.Amount;
import name.pehl.gwt.piriti.client.model.Model;
import name.pehl.gwt.piriti.client.model.NestedModel;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.xml.client.Document;

/**
 * @author $Author$
 * @version $Revision$
 */
public class GwtTestXmlModelReader extends GWTTestCase
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
        new Model();
        new NestedModel();

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
        Document document = XmlModelFactory.createModel();
        Model demo = Model.XML.readSingle(document);

        // Primitives and simple objects
        assertNotNull(demo);
        assertTrue(demo.booleanPrimitive);
        assertTrue(demo.booleanObject);
        assertEquals(1, demo.bytePrimitive);
        assertEquals(2, demo.byteObject.byteValue());
        assertEquals('a', demo.characterPrimitive);
        assertEquals('b', demo.characterObject.charValue());
        assertEquals(MY_BIRTHDAY, demo.date);
        assertEquals(Amount.THREE, demo.demoEnum);
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
        assertNull(demo.model);
        assertNestedModel(demo.nestedModel);

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
        assertEquals(3, demo.arrayOfNestedModels.length);
        for (int i = 0; i < 3; i++)
        {
            assertNestedModel(demo.arrayOfNestedModels[i]);
        }

        // Invalid arrays
        assertNull(demo.arrayOfModels);
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
        assertEquals(3, demo.collectionOfNestedModels.size());
        for (NestedModel nm : demo.collectionOfNestedModels)
        {
            assertNestedModel(nm);
        }

        // Invalid collections
        assertNull(demo.untypedCollection);
        assertNull(demo.collectionOfModels);
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
        assertEquals(3, demo.listOfNestedModels.size());
        for (int i = 0; i < 3; i++)
        {
            assertNestedModel(demo.listOfNestedModels.get(i));
        }

        // Invalid lists
        assertNull(demo.untypedList);
        assertNull(demo.listOfModels);
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
        assertEquals(3, demo.setOfNestedModels.size());
        for (NestedModel nm : demo.setOfNestedModels)
        {
            assertNestedModel(nm);
        }

        // Invalid sets
        assertNull(demo.untypedSet);
        assertNull(demo.setOfModels);
        assertNull(demo.setOfArrays);
        assertNull(demo.setOfSets);
        assertNull(demo.setOfMaps);
    }


    private void assertNestedModel(NestedModel nestedModel)
    {
        assertNotNull(nestedModel);
        assertTrue(nestedModel.booleanPrimitive);
        assertTrue(nestedModel.booleanObject);
        assertEquals(1, nestedModel.bytePrimitive);
        assertEquals(2, nestedModel.byteObject.byteValue());
        assertEquals('a', nestedModel.characterPrimitive);
        assertEquals('b', nestedModel.characterObject.charValue());
        assertEquals(MY_BIRTHDAY, nestedModel.date);
        assertEquals(Amount.THREE, nestedModel.demoEnum);
        assertEquals(4.5, nestedModel.doublePrimitive);
        assertEquals(6.7, nestedModel.doubleObject.doubleValue());
        assertEquals(8.9f, nestedModel.floatPrimitive);
        assertEquals(10.11f, nestedModel.floatObject.floatValue());
        assertEquals(12, nestedModel.integerPrimitive);
        assertEquals(13, nestedModel.integerObject.intValue());
        assertEquals(14l, nestedModel.longPrimitive);
        assertEquals(15l, nestedModel.longObject.longValue());
        assertEquals(16, nestedModel.shortPrimitive);
        assertEquals(17, nestedModel.shortObject.shortValue());
        assertEquals("achtzehn", nestedModel.string);
        assertEquals("neunzehn", nestedModel.stringAttribute);
    }
}
