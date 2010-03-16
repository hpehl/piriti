package name.pehl.piriti.client.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public abstract class ModelTestCase extends GWTTestCase
{
    protected static final Date MY_BIRTHDAY = new Date(115813353000l);

    protected Set<Integer> setOfIntegerObjectsFixture;
    protected Set<String> setOfStringsFixture;


    @Override
    public String getModuleName()
    {
        return "name.pehl.piriti.PiritiTest";
    }


    @Override
    protected void gwtSetUp() throws Exception
    {
        System.out.println("Running " + getClass().getName());

        // Register readers
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


    protected void assertModel(Model model)
    {
        // Primitives and simple objects
        assertNotNull(model);
        assertTrue(model.booleanPrimitive);
        assertTrue(model.booleanObject);
        assertEquals(1, model.bytePrimitive);
        assertEquals(2, model.byteObject.byteValue());
        assertEquals('a', model.characterPrimitive);
        assertEquals('b', model.characterObject.charValue());
        assertEquals(MY_BIRTHDAY, model.date);
        assertEquals(Amount.THREE, model.amount);
        assertEquals(4.5, model.doublePrimitive);
        assertEquals(6.7, model.doubleObject.doubleValue());
        assertEquals(8.9f, model.floatPrimitive);
        assertEquals(10.11f, model.floatObject.floatValue());
        assertEquals(12, model.integerPrimitive);
        assertEquals(13, model.integerObject.intValue());
        assertEquals(14l, model.longPrimitive);
        assertEquals(15l, model.longObject.longValue());
        assertEquals(16, model.shortPrimitive);
        assertEquals(17, model.shortObject.shortValue());
        assertEquals("achtzehn", model.string);
        assertEquals("neunzehn", model.stringAttribute);

        // Nested objects
        assertNull(model.model);
        assertNestedModel(model.nestedModel);

        // Arrays
        assertEquals(3, model.arrayOfIntegerPrimitives.length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(i, model.arrayOfIntegerPrimitives[i]);
        }
        assertEquals(3, model.arrayOfIntegerObjects.length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(i, model.arrayOfIntegerObjects[i].intValue());
        }
        assertEquals(3, model.arrayOfStrings.length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(String.valueOf(i), model.arrayOfStrings[i]);
        }
        assertEquals(3, model.arrayOfNestedModels.length);
        for (int i = 0; i < 3; i++)
        {
            assertNestedModel(model.arrayOfNestedModels[i]);
        }

        // Invalid arrays
        assertNull(model.arrayOfModels);
        assertNull(model.arrayOfLists);
        assertNull(model.arrayOfMaps);
        assertNull(model.multiDimensionalIntegerPrimitiveArray);
        assertNull(model.multiDimensionalIntegerObjectArray);

        // Collections
        assertEquals(3, model.collectionOfIntegerObjects.size());
        model.collectionOfIntegerObjects.removeAll(setOfIntegerObjectsFixture);
        assertTrue(model.collectionOfIntegerObjects.isEmpty());
        assertEquals(3, model.collectionOfStrings.size());
        model.collectionOfStrings.removeAll(setOfStringsFixture);
        assertTrue(model.collectionOfStrings.isEmpty());
        assertEquals(3, model.collectionOfNestedModels.size());
        for (NestedModel nm : model.collectionOfNestedModels)
        {
            assertNestedModel(nm);
        }

        // Invalid collections
        assertNull(model.untypedCollection);
        assertNull(model.collectionOfModels);
        assertNull(model.collectionOfArrays);
        assertNull(model.collectionOfCollections);
        assertNull(model.collectionOfMaps);

        // Lists
        assertEquals(3, model.listOfIntegerObjects.size());
        for (int i = 0; i < 3; i++)
        {
            assertEquals(i, model.listOfIntegerObjects.get(i).intValue());
        }
        assertEquals(3, model.listOfStrings.size());
        for (int i = 0; i < 3; i++)
        {
            assertEquals(String.valueOf(i), model.listOfStrings.get(i));
        }
        assertEquals(3, model.listOfNestedModels.size());
        for (int i = 0; i < 3; i++)
        {
            assertNestedModel(model.listOfNestedModels.get(i));
        }

        // Invalid lists
        assertNull(model.untypedList);
        assertNull(model.listOfModels);
        assertNull(model.listOfArrays);
        assertNull(model.listOfLists);
        assertNull(model.listOfMaps);

        // Sets
        assertEquals(3, model.setOfIntegerObjects.size());
        model.setOfIntegerObjects.removeAll(setOfIntegerObjectsFixture);
        assertTrue(model.setOfIntegerObjects.isEmpty());
        assertEquals(3, model.setOfStrings.size());
        model.setOfStrings.removeAll(setOfStringsFixture);
        assertTrue(model.setOfStrings.isEmpty());
        assertEquals(3, model.setOfNestedModels.size());
        for (NestedModel nm : model.setOfNestedModels)
        {
            assertNestedModel(nm);
        }

        // Invalid sets
        assertNull(model.untypedSet);
        assertNull(model.setOfModels);
        assertNull(model.setOfArrays);
        assertNull(model.setOfSets);
        assertNull(model.setOfMaps);
    }


    protected void assertNestedModel(NestedModel nestedModel)
    {
        assertNotNull(nestedModel);
        assertTrue(nestedModel.booleanPrimitive);
        assertTrue(nestedModel.booleanObject);
        assertEquals(1, nestedModel.bytePrimitive);
        assertEquals(2, nestedModel.byteObject.byteValue());
        assertEquals('a', nestedModel.characterPrimitive);
        assertEquals('b', nestedModel.characterObject.charValue());
        assertEquals(MY_BIRTHDAY, nestedModel.date);
        assertEquals(Amount.THREE, nestedModel.amount);
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
