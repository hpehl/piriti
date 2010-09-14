package name.pehl.piriti.client.gwttest.fat;

import static name.pehl.piriti.client.gwttest.fat.FatGlobalItemResources.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import name.pehl.piriti.client.gwttest.AbstractPiritiTest;

/**
 * @author $Author$
 * @version $Date$ $Revision: 295
 *          $
 */
public abstract class AbstractFatGlobalItemTest extends AbstractPiritiTest
{
    protected Set<Integer> setOfIntegerObjectsFixture;
    protected Set<String> setOfStringsFixture;


    @Override
    protected void gwtSetUp() throws Exception
    {
        super.gwtSetUp();

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


    protected void assertFatGlobalItems(Collection<FatGlobalItem> items, boolean withNestedFatGlobalItems)
    {
        assertNotNull(items);
        assertEquals(SIZE, items.size());
        for (FatGlobalItem fgi : items)
        {
            assertFatGlobalItem(fgi, withNestedFatGlobalItems);
        }
    }


    protected void assertFatGlobalItem(FatGlobalItem fgi, boolean withNestedFatGlobalItems)
    {
        // Primitives and simple objects
        assertNotNull(fgi);
        assertTrue(fgi.booleanPrimitive);
        assertTrue(fgi.booleanObject);
        assertEquals(1, fgi.bytePrimitive);
        assertEquals(2, fgi.byteObject.byteValue());
        assertEquals('a', fgi.characterPrimitive);
        assertEquals('b', fgi.characterObject.charValue());
        assertDate(MY_BIRTHDAY, fgi.date);
        assertEquals(Amount.THREE, fgi.amount);
        assertEquals(4.5, fgi.doublePrimitive, .05);
        assertEquals(6.7, fgi.doubleObject.doubleValue(), .05);
        assertEquals(8.9f, fgi.floatPrimitive, .05f);
        assertEquals(10.11f, fgi.floatObject.floatValue(), .05f);
        assertEquals(12, fgi.integerPrimitive);
        assertEquals(13, fgi.integerObject.intValue());
        assertEquals(14l, fgi.longPrimitive);
        assertEquals(15l, fgi.longObject.longValue());
        assertEquals(16, fgi.shortPrimitive);
        assertEquals(17, fgi.shortObject.shortValue());
        assertEquals("achtzehn", fgi.string);
        assertEquals("neunzehn", fgi.stringAttribute);

        // Nested objects
        if (withNestedFatGlobalItems)
        {
            assertFatGlobalItem(fgi.fatGlobalItem, false);
        }
        assertSkinnyNestedItem(fgi.skinnyNestedItem);

        // Arrays
        assertEquals(SIZE, fgi.arrayOfIntegerPrimitives.length);
        for (int i = 0; i < SIZE; i++)
        {
            assertEquals(i, fgi.arrayOfIntegerPrimitives[i]);
        }
        assertEquals(SIZE, fgi.arrayOfIntegerObjects.length);
        for (int i = 0; i < SIZE; i++)
        {
            assertEquals(i, fgi.arrayOfIntegerObjects[i].intValue());
        }
        assertEquals(SIZE, fgi.arrayOfStrings.length);
        for (int i = 0; i < SIZE; i++)
        {
            assertEquals(String.valueOf(i), fgi.arrayOfStrings[i]);
        }
        if (withNestedFatGlobalItems)
        {
            assertNotNull(fgi.arrayOfFatGlobalItems);
            assertFatGlobalItems(Arrays.asList(fgi.arrayOfFatGlobalItems), false);
        }
        assertEquals(SIZE, fgi.arrayOfSkinnyNestedItems.length);
        for (int i = 0; i < SIZE; i++)
        {
            assertSkinnyNestedItem(fgi.arrayOfSkinnyNestedItems[i]);
        }

        // Invalid arrays
        assertNull(fgi.arrayOfLists);
        assertNull(fgi.arrayOfMaps);
        assertNull(fgi.multiDimensionalIntegerPrimitiveArray);
        assertNull(fgi.multiDimensionalIntegerObjectArray);

        // Collections
        assertEquals(SIZE, fgi.collectionOfIntegerObjects.size());
        fgi.collectionOfIntegerObjects.removeAll(setOfIntegerObjectsFixture);
        assertTrue(fgi.collectionOfIntegerObjects.isEmpty());
        assertEquals(SIZE, fgi.collectionOfStrings.size());
        fgi.collectionOfStrings.removeAll(setOfStringsFixture);
        assertTrue(fgi.collectionOfStrings.isEmpty());
        if (withNestedFatGlobalItems)
        {
            assertFatGlobalItems(fgi.collectionOfFatGlobalItems, false);
        }
        assertEquals(SIZE, fgi.collectionOfSkinnyNestedItems.size());
        for (SkinnyNestedItem sni : fgi.collectionOfSkinnyNestedItems)
        {
            assertSkinnyNestedItem(sni);
        }

        // Invalid collections
        assertNull(fgi.untypedCollection);
        assertNull(fgi.collectionOfArrays);
        assertNull(fgi.collectionOfCollections);
        assertNull(fgi.collectionOfMaps);

        // Lists
        assertEquals(SIZE, fgi.listOfIntegerObjects.size());
        for (int i = 0; i < SIZE; i++)
        {
            assertEquals(i, fgi.listOfIntegerObjects.get(i).intValue());
        }
        assertEquals(SIZE, fgi.listOfStrings.size());
        for (int i = 0; i < SIZE; i++)
        {
            assertEquals(String.valueOf(i), fgi.listOfStrings.get(i));
        }
        if (withNestedFatGlobalItems)
        {
            assertFatGlobalItems(fgi.listOfFatGlobalItems, false);
        }
        assertEquals(SIZE, fgi.listOfSkinnyNestedItems.size());
        for (int i = 0; i < SIZE; i++)
        {
            assertSkinnyNestedItem(fgi.listOfSkinnyNestedItems.get(i));
        }

        // Invalid lists
        assertNull(fgi.untypedList);
        assertNull(fgi.listOfArrays);
        assertNull(fgi.listOfLists);
        assertNull(fgi.listOfMaps);

        // Sets
        assertEquals(SIZE, fgi.setOfIntegerObjects.size());
        fgi.setOfIntegerObjects.removeAll(setOfIntegerObjectsFixture);
        assertTrue(fgi.setOfIntegerObjects.isEmpty());
        assertEquals(SIZE, fgi.setOfStrings.size());
        fgi.setOfStrings.removeAll(setOfStringsFixture);
        assertTrue(fgi.setOfStrings.isEmpty());
        if (withNestedFatGlobalItems)
        {
            assertFatGlobalItems(fgi.setOfFatGlobalItems, false);
        }
        assertEquals(SIZE, fgi.setOfSkinnyNestedItems.size());
        for (SkinnyNestedItem sni : fgi.setOfSkinnyNestedItems)
        {
            assertSkinnyNestedItem(sni);
        }

        // Invalid sets
        assertNull(fgi.untypedSet);
        assertNull(fgi.setOfArrays);
        assertNull(fgi.setOfSets);
        assertNull(fgi.setOfMaps);
    }


    protected void assertSkinnyNestedItem(SkinnyNestedItem sni)
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
        assertEquals(4.5, sni.doublePrimitive, .05);
        assertEquals(6.7, sni.doubleObject.doubleValue(), .05);
        assertEquals(8.9f, sni.floatPrimitive, .05f);
        assertEquals(10.11f, sni.floatObject.floatValue(), .05f);
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
