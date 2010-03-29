package name.pehl.piriti.client.gwttest.fat.json;

import static name.pehl.piriti.client.gwttest.JsonFactoryHelper.*;
import static name.pehl.piriti.client.gwttest.fat.FatGlobalItemTestCase.*;

import java.util.Date;

import name.pehl.piriti.client.converter.DateConverter;
import name.pehl.piriti.client.gwttest.fat.Amount;

import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public final class JsonFatGlobalItemFactory
{
    private static final Date MY_BIRTHDAY = new Date(115813353000l);


    private JsonFatGlobalItemFactory()
    {
    }


    /**
     * Create FatGlobalItems with nested FatGlobalItems.
     * 
     * @return
     */
    public static String createFatGlobalItems()
    {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append(ITEMS);
        json.append(": [");
        for (int i = 0; i < SIZE; i++)
        {
            appendValue(json, createFatGlobalItem(), false, (i < SIZE - 1));
        }
        json.append("]}");
        return json.toString();
    }


    /**
     * Create a FatGlobalItem with nested FatGlobalItems.
     * 
     * @return
     */
    public static String createFatGlobalItem()
    {
        return createFatGlobalItem(true);
    }


    /**
     * Create a FatGlobalItem. Depending on the parameter {@code
     * withNestedFatGlobalItems} nested FatGlobalItems are also generated.
     * 
     * @param withNestedFatGlobalItems
     * @return
     */
    private static String createFatGlobalItem(boolean withNestedFatGlobalItems)
    {
        StringBuilder json = new StringBuilder();
        json.append("{");

        // Primitives and simple objects
        appendSimpleObjects(json);
        json.append(", ");

        // Nested objects
        if (withNestedFatGlobalItems)
        {
            json.append("fatGlobalItem: ");
            json.append(createFatGlobalItem(false));
            json.append(", ");
        }
        json.append("skinnyNestedItem: {");
        appendSimpleObjects(json);
        json.append("}, ");

        // Arrays
        appendKeyValueArray(json, "arrayOfIntegerPrimitives", false, true, "0", "1", "2");
        appendKeyValueArray(json, "arrayOfIntegerObjects", false, true, "0", "1", "2");
        appendKeyValueArray(json, "arrayOfStrings", true, true, "0", "1", "2");
        if (withNestedFatGlobalItems)
        {
            appendFatGlobalItmes(json, "arrayOfFatGlobalItems", SIZE, true);
        }
        appendSkinnyNestedItems(json, "arrayOfSkinnyNestedItems", SIZE, true);

        // Collections
        appendKeyValueArray(json, "collectionOfIntegerObjects", false, true, "0", "1", "2");
        appendKeyValueArray(json, "collectionOfStrings", true, true, "0", "1", "2");
        if (withNestedFatGlobalItems)
        {
            appendFatGlobalItmes(json, "collectionOfFatGlobalItems", SIZE, true);
        }
        appendSkinnyNestedItems(json, "collectionOfSkinnyNestedItems", SIZE, true);

        // Lists
        appendKeyValueArray(json, "listOfIntegerObjects", false, true, "0", "1", "2");
        appendKeyValueArray(json, "listOfStrings", true, true, "0", "1", "2");
        if (withNestedFatGlobalItems)
        {
            appendFatGlobalItmes(json, "listOfFatGlobalItems", SIZE, true);
        }
        appendSkinnyNestedItems(json, "listOfSkinnyNestedItems", SIZE, true);

        // Sets
        appendKeyValueArray(json, "setOfIntegerObjects", false, true, "0", "1", "2");
        appendKeyValueArray(json, "setOfStrings", true, true, "0", "1", "2");
        if (withNestedFatGlobalItems)
        {
            appendFatGlobalItmes(json, "setOfFatGlobalItems", SIZE, true);
        }
        appendSkinnyNestedItems(json, "setOfSkinnyNestedItems", SIZE, false);

        json.append("}");
        return json.toString();
    }


    private static void appendFatGlobalItmes(StringBuilder json, String key, int size, boolean goon)
    {
        json.append(key).append(": [");
        for (int i = 0; i < size; i++)
        {
            json.append(createFatGlobalItem(false));
            if (i < size - 1)
            {
                json.append(", ");
            }
        }
        json.append("]");
        if (goon)
        {
            json.append(", ");
        }
    }


    private static void appendSkinnyNestedItems(StringBuilder json, String key, int size, boolean goon)
    {
        json.append(key).append(": [");
        for (int i = 0; i < size; i++)
        {
            json.append("{");
            appendSimpleObjects(json);
            json.append("}");
            if (i < size - 1)
            {
                json.append(", ");
            }
        }
        json.append("]");
        if (goon)
        {
            json.append(", ");
        }
    }


    private static void appendSimpleObjects(StringBuilder json)
    {
        appendKeyValue(json, "booleanPrimitive", String.valueOf(true), false, true);
        appendKeyValue(json, "booleanObject", String.valueOf(true), false, true);
        appendKeyValue(json, "bytePrimitive", String.valueOf(1), false, true);
        appendKeyValue(json, "byteObject", String.valueOf(2), false, true);
        appendKeyValue(json, "characterPrimitive", "a", true, true);
        appendKeyValue(json, "characterObject", "b", true, true);
        appendKeyValue(json, "date", DateTimeFormat.getFormat(DateConverter.DEFAULT_FORMAT).format(MY_BIRTHDAY), true,
                true);
        appendKeyValue(json, "amount", Amount.THREE.name(), true, true);
        appendKeyValue(json, "doublePrimitive", String.valueOf(4.5), false, true);
        appendKeyValue(json, "doubleObject", String.valueOf(6.7), false, true);
        appendKeyValue(json, "floatPrimitive", String.valueOf(8.9), false, true);
        appendKeyValue(json, "floatObject", String.valueOf(10.11), false, true);
        appendKeyValue(json, "integerPrimitive", String.valueOf(12), false, true);
        appendKeyValue(json, "integerObject", String.valueOf(13), false, true);
        appendKeyValue(json, "longPrimitive", String.valueOf(14), false, true);
        appendKeyValue(json, "longObject", String.valueOf(15), false, true);
        appendKeyValue(json, "shortPrimitive", String.valueOf(16), false, true);
        appendKeyValue(json, "shortObject", String.valueOf(17), false, true);
        appendKeyValue(json, "string", "achtzehn", true, true);
        appendKeyValue(json, "stringAttribute", "neunzehn", true, false);
    }
}
