package name.pehl.piriti.client.types;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

/**
 * @author $Author$
 * @version $Revision$
 */
public class JsonFatGlobalItemTest extends AbstractFatGlobalItemTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String json = FatGlobalItemResources.INSTANCE.fatGlobalItemJson().getText();
        FatGlobalItem fgi = FatGlobalItem.JSON_READER.read(json);
        assertFatGlobalItem(fgi, true);
    }


    // ------------------------------------------------------------ write tests

    public void testWriteEmptyFatGlobalItem()
    {
        FatGlobalItem fgi = new FatGlobalItem();
        String json = FatGlobalItem.JSON_WRITER.toJson(fgi);
        assertNotNull(json);

        JSONValue jsonValue = JSONParser.parseStrict(json);
        assertNotNull(jsonValue);

        JSONObject jsonObject = jsonValue.isObject();
        assertNotNull(jsonObject);

        // primitives and wrapper types
        assertTrue(jsonObject.containsKey("booleanPrimitive"));
        assertNotNull(jsonObject.get("booleanPrimitive").isBoolean());
        assertFalse(jsonObject.get("booleanPrimitive").isBoolean().booleanValue());
        assertTrue(jsonObject.containsKey("booleanObject"));
        assertNotNull(jsonObject.get("booleanObject").isBoolean());
        assertFalse(jsonObject.get("booleanObject").isBoolean().booleanValue());
        assertTrue(jsonObject.containsKey("bytePrimitive"));
        assertNotNull(jsonObject.get("bytePrimitive").isNumber());
        assertEquals((byte) 0, (byte) jsonObject.get("bytePrimitive").isNumber().doubleValue());
        assertTrue(jsonObject.containsKey("byteObject"));
        assertNotNull(jsonObject.get("byteObject").isNumber());
        assertEquals((byte) 0, (byte) jsonObject.get("byteObject").isNumber().doubleValue());
        assertTrue(jsonObject.containsKey("characterPrimitive"));
        assertNotNull(jsonObject.get("characterPrimitive").isString());
        assertEquals(String.valueOf('\0'), jsonObject.get("characterPrimitive").isString().stringValue());
        assertTrue(jsonObject.containsKey("characterObject"));
        // assertNotNull(jsonObject.get("characterObject").isString());
        // assertEquals(String.valueOf('\0'),
        // jsonObject.get("characterObject").isString().stringValue());
        assertTrue(jsonObject.containsKey("doublePrimitive"));
        assertNotNull(jsonObject.get("doublePrimitive").isNumber());
        assertEquals(0.0, jsonObject.get("doublePrimitive").isNumber().doubleValue());
        assertTrue(jsonObject.containsKey("doubleObject"));
        assertNotNull(jsonObject.get("doubleObject").isNumber());
        assertEquals(0.0, jsonObject.get("doubleObject").isNumber().doubleValue());
        assertTrue(jsonObject.containsKey("floatPrimitive"));
        assertNotNull(jsonObject.get("floatPrimitive").isNumber());
        assertEquals((float) 0.0, (float) jsonObject.get("floatPrimitive").isNumber().doubleValue());
        assertTrue(jsonObject.containsKey("floatObject"));
        assertNotNull(jsonObject.get("floatObject").isNumber());
        assertEquals((float) 0.0, (float) jsonObject.get("floatObject").isNumber().doubleValue());
        assertTrue(jsonObject.containsKey("integerPrimitive"));
        assertNotNull(jsonObject.get("integerPrimitive").isNumber());
        assertEquals(0, (int) jsonObject.get("integerPrimitive").isNumber().doubleValue());
        assertTrue(jsonObject.containsKey("integerObject"));
        assertNotNull(jsonObject.get("integerObject").isNumber());
        assertEquals(0, (int) jsonObject.get("integerObject").isNumber().doubleValue());
        assertTrue(jsonObject.containsKey("longPrimitive"));
        assertNotNull(jsonObject.get("longPrimitive").isNumber());
        assertEquals(0l, (long) jsonObject.get("longPrimitive").isNumber().doubleValue());
        assertTrue(jsonObject.containsKey("longObject"));
        assertNotNull(jsonObject.get("longObject").isNumber());
        assertEquals(0l, (long) jsonObject.get("longObject").isNumber().doubleValue());
        assertTrue(jsonObject.containsKey("shortPrimitive"));
        assertNotNull(jsonObject.get("shortPrimitive").isNumber());
        assertEquals((short) 0, (short) jsonObject.get("shortPrimitive").isNumber().doubleValue());
        assertTrue(jsonObject.containsKey("shortObject"));
        assertNotNull(jsonObject.get("shortObject").isNumber());
        assertEquals((short) 0, (short) jsonObject.get("shortObject").isNumber().doubleValue());

        // null values
        assertTrue(jsonObject.containsKey("date"));
        assertNotNull(jsonObject.get("date").isNull());
        assertTrue(jsonObject.containsKey("sqlDate"));
        assertNotNull(jsonObject.get("sqlDate").isNull());
        assertTrue(jsonObject.containsKey("time"));
        assertNotNull(jsonObject.get("time").isNull());
        assertTrue(jsonObject.containsKey("timestamp"));
        assertNotNull(jsonObject.get("timestamp").isNull());
        assertTrue(jsonObject.containsKey("amount"));
        assertNotNull(jsonObject.get("amount").isNull());
        assertTrue(jsonObject.containsKey("string"));
        assertNotNull(jsonObject.get("string").isNull());
        assertTrue(jsonObject.containsKey("stringAttribute"));
        assertNotNull(jsonObject.get("stringAttribute").isNull());
        assertTrue(jsonObject.containsKey("fatGlobalItem"));
        assertNotNull(jsonObject.get("fatGlobalItem").isNull());
        assertTrue(jsonObject.containsKey("skinnyNestedItem"));
        assertNotNull(jsonObject.get("skinnyNestedItem").isNull());
        assertTrue(jsonObject.containsKey("arrayOfIntegerPrimitives"));
        assertNotNull(jsonObject.get("arrayOfIntegerPrimitives").isNull());
        assertTrue(jsonObject.containsKey("arrayOfIntegerObjects"));
        assertNotNull(jsonObject.get("arrayOfIntegerObjects").isNull());
        assertTrue(jsonObject.containsKey("arrayOfStrings"));
        assertNotNull(jsonObject.get("arrayOfStrings").isNull());
        assertTrue(jsonObject.containsKey("arrayOfFatGlobalItems"));
        assertNotNull(jsonObject.get("arrayOfFatGlobalItems").isNull());
        assertTrue(jsonObject.containsKey("arrayOfSkinnyNestedItems"));
        assertNotNull(jsonObject.get("arrayOfSkinnyNestedItems").isNull());
        assertTrue(jsonObject.containsKey("collectionOfIntegerObjects"));
        assertNotNull(jsonObject.get("collectionOfIntegerObjects").isNull());
        assertTrue(jsonObject.containsKey("collectionOfStrings"));
        assertNotNull(jsonObject.get("collectionOfStrings").isNull());
        assertTrue(jsonObject.containsKey("collectionOfFatGlobalItems"));
        assertNotNull(jsonObject.get("collectionOfFatGlobalItems").isNull());
        assertTrue(jsonObject.containsKey("collectionOfSkinnyNestedItems"));
        assertNotNull(jsonObject.get("collectionOfSkinnyNestedItems").isNull());
        assertTrue(jsonObject.containsKey("listOfIntegerObjects"));
        assertNotNull(jsonObject.get("listOfIntegerObjects").isNull());
        assertTrue(jsonObject.containsKey("listOfStrings"));
        assertNotNull(jsonObject.get("listOfStrings").isNull());
        assertTrue(jsonObject.containsKey("arrayListOfStrings"));
        assertNotNull(jsonObject.get("arrayListOfStrings").isNull());
        assertTrue(jsonObject.containsKey("linkedListOfStrings"));
        assertNotNull(jsonObject.get("linkedListOfStrings").isNull());
        assertTrue(jsonObject.containsKey("listOfFatGlobalItems"));
        assertNotNull(jsonObject.get("listOfFatGlobalItems").isNull());
        assertTrue(jsonObject.containsKey("listOfSkinnyNestedItems"));
        assertNotNull(jsonObject.get("listOfSkinnyNestedItems").isNull());
        assertTrue(jsonObject.containsKey("setOfIntegerObjects"));
        assertNotNull(jsonObject.get("setOfIntegerObjects").isNull());
        assertTrue(jsonObject.containsKey("setOfStrings"));
        assertNotNull(jsonObject.get("setOfStrings").isNull());
        assertTrue(jsonObject.containsKey("hashSetOfStrings"));
        assertNotNull(jsonObject.get("hashSetOfStrings").isNull());
        assertTrue(jsonObject.containsKey("linkedHashSetOfStrings"));
        assertNotNull(jsonObject.get("linkedHashSetOfStrings").isNull());
        assertTrue(jsonObject.containsKey("treeSetOfStrings"));
        assertNotNull(jsonObject.get("treeSetOfStrings").isNull());
        assertTrue(jsonObject.containsKey("setOfFatGlobalItems"));
        assertNotNull(jsonObject.get("setOfFatGlobalItems").isNull());
        assertTrue(jsonObject.containsKey("setOfSkinnyNestedItems"));
        assertNotNull(jsonObject.get("setOfSkinnyNestedItems").isNull());

        // invalid properties
        assertFalse(jsonObject.containsKey("arrayOfLists"));
        assertFalse(jsonObject.containsKey("arrayOfMaps"));
        assertFalse(jsonObject.containsKey("multiDimensionalIntegerPrimitiveArray"));
        assertFalse(jsonObject.containsKey("multiDimensionalIntegerObjectArray"));
        assertFalse(jsonObject.containsKey("untypedCollection"));
        assertFalse(jsonObject.containsKey("collectionOfArrays"));
        assertFalse(jsonObject.containsKey("collectionOfCollections"));
        assertFalse(jsonObject.containsKey("collectionOfMaps"));
        assertFalse(jsonObject.containsKey("untypedList"));
        assertFalse(jsonObject.containsKey("listOfArrays"));
        assertFalse(jsonObject.containsKey("listOfLists"));
        assertFalse(jsonObject.containsKey("listOfMaps"));
        assertFalse(jsonObject.containsKey("untypedSet"));
        assertFalse(jsonObject.containsKey("setOfArrays"));
        assertFalse(jsonObject.containsKey("setOfSets"));
        assertFalse(jsonObject.containsKey("setOfMaps"));
    }
}
