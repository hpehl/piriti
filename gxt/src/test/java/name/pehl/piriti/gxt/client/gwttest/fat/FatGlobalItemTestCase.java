package name.pehl.piriti.gxt.client.gwttest.fat;

import java.util.Collection;
import java.util.Date;
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
public abstract class FatGlobalItemTestCase extends GWTTestCase
{
    public static final int SIZE = 3;
    public static final String ITEMS = "items";
    public static final Date MY_BIRTHDAY = new Date(115813353000l);

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


    protected void assertFatGlobalItems(List<FatGlobalItem> items)
    {
        assertNotNull(items);
        assertEquals(SIZE, items.size());
        for (FatGlobalItem fgi : items)
        {
            assertFatGlobalItem(fgi);
        }
    }
    
    
    protected void assertFatGlobalItem(FatGlobalItem fgm)
    {
        // Basic stuff
        assertBasicStuff(fgm);

        // Nested objects
        SkinnyNestedItem skinnyNestedItem = fgm.get("skinnyNestedItem");
        assertNotNull(skinnyNestedItem);
        assertBasicStuff(skinnyNestedItem);

        // Arrays
        Integer[] arrayOfIntegerObjects = fgm.get("arrayOfIntegerObjects");
        assertEquals(SIZE, arrayOfIntegerObjects.length);
        for (int i = 0; i < SIZE; i++)
        {
            assertEquals(i, arrayOfIntegerObjects[i].intValue());
        }
        String[] arrayOfStrings = fgm.get("arrayOfStrings");
        assertEquals(SIZE, arrayOfStrings.length);
        for (int i = 0; i < SIZE; i++)
        {
            assertEquals(String.valueOf(i), arrayOfStrings[i]);
        }
        SkinnyNestedItem[] arrayOfSkinnyNestedItems = fgm.get("arrayOfSkinnyNestedItems");
        assertEquals(SIZE, arrayOfSkinnyNestedItems.length);
        for (int i = 0; i < SIZE; i++)
        {
            assertBasicStuff(arrayOfSkinnyNestedItems[i]);
        }

        // Collections
        Collection<Integer> collectionOfIntegerObjects = fgm.get("collectionOfIntegerObjects");
        assertEquals(SIZE, collectionOfIntegerObjects.size());
        collectionOfIntegerObjects.removeAll(setOfIntegerObjectsFixture);
        assertTrue(collectionOfIntegerObjects.isEmpty());
        Collection<String> collectionOfStrings = fgm.get("collectionOfStrings");
        assertEquals(SIZE, collectionOfStrings.size());
        collectionOfStrings.removeAll(setOfStringsFixture);
        assertTrue(collectionOfStrings.isEmpty());
        Collection<SkinnyNestedItem> collectionOfSkinnyNestedItems = fgm.get("collectionOfSkinnyNestedItems");
        assertEquals(SIZE, collectionOfSkinnyNestedItems.size());
        for (SkinnyNestedItem sni : collectionOfSkinnyNestedItems)
        {
            assertBasicStuff(sni);
        }

        // Lists
        List<Integer> listOfIntegerObjects = fgm.get("listOfIntegerObjects");
        assertEquals(SIZE, listOfIntegerObjects.size());
        for (int i = 0; i < SIZE; i++)
        {
            assertEquals(i, listOfIntegerObjects.get(i).intValue());
        }
        List<String> listOfStrings = fgm.get("listOfStrings");
        assertEquals(SIZE, listOfStrings.size());
        for (int i = 0; i < SIZE; i++)
        {
            assertEquals(String.valueOf(i), listOfStrings.get(i));
        }
        List<SkinnyNestedItem> listOfSkinnyNestedItems = fgm.get("listOfSkinnyNestedItems");
        assertEquals(SIZE, listOfSkinnyNestedItems.size());
        for (int i = 0; i < SIZE; i++)
        {
            assertBasicStuff(listOfSkinnyNestedItems.get(i));
        }

        // Sets
        Set<Integer> setOfIntegerObjects = fgm.get("setOfIntegerObjects");
        assertEquals(SIZE, setOfIntegerObjects.size());
        setOfIntegerObjects.removeAll(setOfIntegerObjectsFixture);
        assertTrue(setOfIntegerObjects.isEmpty());
        Set<String> setOfStrings = fgm.get("setOfStrings");
        assertEquals(SIZE, setOfStrings.size());
        setOfStrings.removeAll(setOfStringsFixture);
        assertTrue(setOfStrings.isEmpty());
        Set<SkinnyNestedItem> setOfSkinnyNestedItems = fgm.get("setOfSkinnyNestedItems");
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
        assertEquals(6.7, ((Double) model.get("doubleObject")).doubleValue());
        assertEquals(10.11f, ((Float) model.get("floatObject")).floatValue());
        assertEquals(13, ((Integer) model.get("integerObject")).intValue());
        assertEquals(15l, ((Long) model.get("longObject")).longValue());
        assertEquals(17, ((Short) model.get("shortObject")).shortValue());
        assertEquals("achtzehn", model.get("string"));
        assertEquals("neunzehn", model.get("stringAttribute"));
    }
}
