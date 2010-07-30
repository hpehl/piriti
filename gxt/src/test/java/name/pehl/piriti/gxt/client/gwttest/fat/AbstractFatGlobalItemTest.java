package name.pehl.piriti.gxt.client.gwttest.fat;

import static name.pehl.piriti.client.gwttest.fat.FatGlobalItemResources.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import name.pehl.piriti.client.gwttest.fat.Amount;

import com.extjs.gxt.ui.client.data.ModelData;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * @author $Author$
 * @version $Date$ $Revision: 237
 *          $
 */
public abstract class AbstractFatGlobalItemTest extends GWTTestCase
{
    protected Set<Integer> setOfIntegerObjectsFixture;
    protected Set<String> setOfStringsFixture;


    @Override
    public String getModuleName()
    {
        return "name.pehl.piriti.gxt.PiritiGxtTest";
    }


    @Override
    protected void gwtSetUp() throws Exception
    {
        System.out.println(getClass().getName() + "." + getName() + "()");

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
        // Basic stuff
        assertBasicStuff(fgi);

        // Nested objects
        if (withNestedFatGlobalItems)
        {
            assertFatGlobalItem((FatGlobalItem) fgi.get("fatGlobalItem"), false);
        }
        SkinnyNestedItem skinnyNestedItem = fgi.get("skinnyNestedItem");
        assertNotNull(skinnyNestedItem);
        assertBasicStuff(skinnyNestedItem);

        // Arrays
        Integer[] arrayOfIntegerObjects = fgi.get("arrayOfIntegerObjects");
        assertEquals(SIZE, arrayOfIntegerObjects.length);
        for (int i = 0; i < SIZE; i++)
        {
            assertEquals(i, arrayOfIntegerObjects[i].intValue());
        }
        String[] arrayOfStrings = fgi.get("arrayOfStrings");
        assertEquals(SIZE, arrayOfStrings.length);
        for (int i = 0; i < SIZE; i++)
        {
            assertEquals(String.valueOf(i), arrayOfStrings[i]);
        }
        if (withNestedFatGlobalItems)
        {
            FatGlobalItem[] arrayOfFatGlobalItems = fgi.get("arrayOfFatGlobalItems");
            assertNotNull(arrayOfFatGlobalItems);
            assertFatGlobalItems(Arrays.asList(arrayOfFatGlobalItems), false);
        }
        SkinnyNestedItem[] arrayOfSkinnyNestedItems = fgi.get("arrayOfSkinnyNestedItems");
        assertEquals(SIZE, arrayOfSkinnyNestedItems.length);
        for (int i = 0; i < SIZE; i++)
        {
            assertBasicStuff(arrayOfSkinnyNestedItems[i]);
        }

        // Collections
        Collection<Integer> collectionOfIntegerObjects = fgi.get("collectionOfIntegerObjects");
        assertEquals(SIZE, collectionOfIntegerObjects.size());
        collectionOfIntegerObjects.removeAll(setOfIntegerObjectsFixture);
        assertTrue(collectionOfIntegerObjects.isEmpty());
        Collection<String> collectionOfStrings = fgi.get("collectionOfStrings");
        assertEquals(SIZE, collectionOfStrings.size());
        collectionOfStrings.removeAll(setOfStringsFixture);
        assertTrue(collectionOfStrings.isEmpty());
        if (withNestedFatGlobalItems)
        {
            Collection<FatGlobalItem> collectionOfFatGlobalItems = fgi.get("collectionOfFatGlobalItems");
            assertFatGlobalItems(collectionOfFatGlobalItems, false);
        }
        Collection<SkinnyNestedItem> collectionOfSkinnyNestedItems = fgi.get("collectionOfSkinnyNestedItems");
        assertEquals(SIZE, collectionOfSkinnyNestedItems.size());
        for (SkinnyNestedItem sni : collectionOfSkinnyNestedItems)
        {
            assertBasicStuff(sni);
        }

        // Lists
        List<Integer> listOfIntegerObjects = fgi.get("listOfIntegerObjects");
        assertEquals(SIZE, listOfIntegerObjects.size());
        for (int i = 0; i < SIZE; i++)
        {
            assertEquals(i, listOfIntegerObjects.get(i).intValue());
        }
        List<String> listOfStrings = fgi.get("listOfStrings");
        assertEquals(SIZE, listOfStrings.size());
        for (int i = 0; i < SIZE; i++)
        {
            assertEquals(String.valueOf(i), listOfStrings.get(i));
        }
        if (withNestedFatGlobalItems)
        {
            List<FatGlobalItem> listOfFatGlobalItems = fgi.get("listOfFatGlobalItems");
            assertFatGlobalItems(listOfFatGlobalItems, false);
        }
        List<SkinnyNestedItem> listOfSkinnyNestedItems = fgi.get("listOfSkinnyNestedItems");
        assertEquals(SIZE, listOfSkinnyNestedItems.size());
        for (int i = 0; i < SIZE; i++)
        {
            assertBasicStuff(listOfSkinnyNestedItems.get(i));
        }

        // Sets
        Set<Integer> setOfIntegerObjects = fgi.get("setOfIntegerObjects");
        assertEquals(SIZE, setOfIntegerObjects.size());
        setOfIntegerObjects.removeAll(setOfIntegerObjectsFixture);
        assertTrue(setOfIntegerObjects.isEmpty());
        Set<String> setOfStrings = fgi.get("setOfStrings");
        assertEquals(SIZE, setOfStrings.size());
        setOfStrings.removeAll(setOfStringsFixture);
        assertTrue(setOfStrings.isEmpty());
        if (withNestedFatGlobalItems)
        {
            Set<FatGlobalItem> setOfFatGlobalItems = fgi.get("setOfFatGlobalItems");
            assertFatGlobalItems(setOfFatGlobalItems, false);
        }
        Set<SkinnyNestedItem> setOfSkinnyNestedItems = fgi.get("setOfSkinnyNestedItems");
        assertEquals(SIZE, setOfSkinnyNestedItems.size());
        for (SkinnyNestedItem sni : setOfSkinnyNestedItems)
        {
            assertBasicStuff(sni);
        }
    }


    protected void assertBasicStuff(ModelData model)
    {
        assertNotNull(model);
        assertTrue(((Boolean) model.get("booleanObject")).booleanValue());
        assertEquals(2, ((Byte) model.get("byteObject")).byteValue());
        assertEquals('b', ((Character) model.get("characterObject")).charValue());
        assertEquals(MY_BIRTHDAY, model.get("date"));
        assertEquals(Amount.THREE, model.get("amount"));
        assertEquals(6.7, ((Double) model.get("doubleObject")).doubleValue(), .05);
        assertEquals(10.11f, ((Float) model.get("floatObject")).floatValue(), .05f);
        assertEquals(13, ((Integer) model.get("integerObject")).intValue());
        assertEquals(15l, ((Long) model.get("longObject")).longValue());
        assertEquals(17, ((Short) model.get("shortObject")).shortValue());
        assertEquals("achtzehn", model.get("string"));
        assertEquals("neunzehn", model.get("stringAttribute"));
    }
}
