package name.pehl.piriti.client.fat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * @author $Author$
 * @version $Date$ $Revision$
 */
public abstract class FatGlobalItemTestCase extends GWTTestCase
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
        new FatGlobalItem();
        new SkinnyNestedItem();

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


    protected void assertFatGlobalItem(FatGlobalItem fgi)
    {
        // Primitives and simple objects
        assertNotNull(fgi);
        assertTrue(fgi.booleanPrimitive);
        assertTrue(fgi.booleanObject);
        assertEquals(1, fgi.bytePrimitive);
        assertEquals(2, fgi.byteObject.byteValue());
        assertEquals('a', fgi.characterPrimitive);
        assertEquals('b', fgi.characterObject.charValue());
        assertEquals(MY_BIRTHDAY, fgi.date);
        assertEquals(Amount.THREE, fgi.amount);
        assertEquals(4.5, fgi.doublePrimitive);
        assertEquals(6.7, fgi.doubleObject.doubleValue());
        assertEquals(8.9f, fgi.floatPrimitive);
        assertEquals(10.11f, fgi.floatObject.floatValue());
        assertEquals(12, fgi.integerPrimitive);
        assertEquals(13, fgi.integerObject.intValue());
        assertEquals(14l, fgi.longPrimitive);
        assertEquals(15l, fgi.longObject.longValue());
        assertEquals(16, fgi.shortPrimitive);
        assertEquals(17, fgi.shortObject.shortValue());
        assertEquals("achtzehn", fgi.string);
        assertEquals("neunzehn", fgi.stringAttribute);

        // Nested objects
        assertNull(fgi.fatGlobalItem);
        assertNestedModel(fgi.skinnyNestedItem);

        // Arrays
        assertEquals(3, fgi.arrayOfIntegerPrimitives.length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(i, fgi.arrayOfIntegerPrimitives[i]);
        }
        assertEquals(3, fgi.arrayOfIntegerObjects.length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(i, fgi.arrayOfIntegerObjects[i].intValue());
        }
        assertEquals(3, fgi.arrayOfStrings.length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(String.valueOf(i), fgi.arrayOfStrings[i]);
        }
        assertEquals(3, fgi.arrayOfSkinnyNestedItems.length);
        for (int i = 0; i < 3; i++)
        {
            assertNestedModel(fgi.arrayOfSkinnyNestedItems[i]);
        }

        // Invalid arrays
        assertNull(fgi.arrayOfFatGlobalItems);
        assertNull(fgi.arrayOfLists);
        assertNull(fgi.arrayOfMaps);
        assertNull(fgi.multiDimensionalIntegerPrimitiveArray);
        assertNull(fgi.multiDimensionalIntegerObjectArray);

        // Collections
        assertEquals(3, fgi.collectionOfIntegerObjects.size());
        fgi.collectionOfIntegerObjects.removeAll(setOfIntegerObjectsFixture);
        assertTrue(fgi.collectionOfIntegerObjects.isEmpty());
        assertEquals(3, fgi.collectionOfStrings.size());
        fgi.collectionOfStrings.removeAll(setOfStringsFixture);
        assertTrue(fgi.collectionOfStrings.isEmpty());
        assertEquals(3, fgi.collectionOfSkinnyNestedItems.size());
        for (SkinnyNestedItem nm : fgi.collectionOfSkinnyNestedItems)
        {
            assertNestedModel(nm);
        }

        // Invalid collections
        assertNull(fgi.untypedCollection);
        assertNull(fgi.collectionOfFatGlobalItems);
        assertNull(fgi.collectionOfArrays);
        assertNull(fgi.collectionOfCollections);
        assertNull(fgi.collectionOfMaps);

        // Lists
        assertEquals(3, fgi.listOfIntegerObjects.size());
        for (int i = 0; i < 3; i++)
        {
            assertEquals(i, fgi.listOfIntegerObjects.get(i).intValue());
        }
        assertEquals(3, fgi.listOfStrings.size());
        for (int i = 0; i < 3; i++)
        {
            assertEquals(String.valueOf(i), fgi.listOfStrings.get(i));
        }
        assertEquals(3, fgi.listOfSkinnyNestedItems.size());
        for (int i = 0; i < 3; i++)
        {
            assertNestedModel(fgi.listOfSkinnyNestedItems.get(i));
        }

        // Invalid lists
        assertNull(fgi.untypedList);
        assertNull(fgi.listOfFatGlobalItems);
        assertNull(fgi.listOfArrays);
        assertNull(fgi.listOfLists);
        assertNull(fgi.listOfMaps);

        // Sets
        assertEquals(3, fgi.setOfIntegerObjects.size());
        fgi.setOfIntegerObjects.removeAll(setOfIntegerObjectsFixture);
        assertTrue(fgi.setOfIntegerObjects.isEmpty());
        assertEquals(3, fgi.setOfStrings.size());
        fgi.setOfStrings.removeAll(setOfStringsFixture);
        assertTrue(fgi.setOfStrings.isEmpty());
        assertEquals(3, fgi.setOfSkinnyNestedItems.size());
        for (SkinnyNestedItem nm : fgi.setOfSkinnyNestedItems)
        {
            assertNestedModel(nm);
        }

        // Invalid sets
        assertNull(fgi.untypedSet);
        assertNull(fgi.setOfFatGlobalItems);
        assertNull(fgi.setOfArrays);
        assertNull(fgi.setOfSets);
        assertNull(fgi.setOfMaps);
    }


    protected void assertNestedModel(SkinnyNestedItem sni)
    {
        assertNotNull(sni);
        assertTrue(sni.booleanPrimitive);
        assertTrue(sni.booleanObject);
        assertEquals(1, sni.bytePrimitive);
        assertEquals(2, sni.byteObject.byteValue());
        assertEquals('a', sni.characterPrimitive);
        assertEquals('b', sni.characterObject.charValue());
        assertEquals(MY_BIRTHDAY, sni.date);
        assertEquals(Amount.THREE, sni.amount);
        assertEquals(4.5, sni.doublePrimitive);
        assertEquals(6.7, sni.doubleObject.doubleValue());
        assertEquals(8.9f, sni.floatPrimitive);
        assertEquals(10.11f, sni.floatObject.floatValue());
        assertEquals(12, sni.integerPrimitive);
        assertEquals(13, sni.integerObject.intValue());
        assertEquals(14l, sni.longPrimitive);
        assertEquals(15l, sni.longObject.longValue());
        assertEquals(16, sni.shortPrimitive);
        assertEquals(17, sni.shortObject.shortValue());
        assertEquals("achtzehn", sni.string);
        assertEquals("neunzehn", sni.stringAttribute);
    }
}
