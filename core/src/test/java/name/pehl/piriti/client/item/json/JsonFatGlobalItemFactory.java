package name.pehl.piriti.client.item.json;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import name.pehl.piriti.client.converter.DateConverter;
import name.pehl.piriti.client.item.Amount;

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


    public static String createJson()
    {
        int size = 3;
        StringBuilder json = new StringBuilder();
        json.append("{");

        // Primitives and simple objects
        appendSimpleObjects(json);
        json.append(", ");

        // SkinnyNestedItem
        json.append("skinnyNestedItem: {");
        appendSimpleObjects(json);
        json.append("}, ");

        // Arrays
        appendKeyValueArray(json, "arrayOfIntegerPrimitives", false, true, "0", "1", "2");
        appendKeyValueArray(json, "arrayOfIntegerObjects", false, true, "0", "1", "2");
        appendKeyValueArray(json, "arrayOfStrings", true, true, "0", "1", "2");
        appendKeyNestedModelsArray(json, "arrayOfSkinnyNestedItems", size, true);

        // Collections
        appendKeyValueArray(json, "collectionOfIntegerObjects", false, true, "0", "1", "2");
        appendKeyValueArray(json, "collectionOfStrings", true, true, "0", "1", "2");
        appendKeyNestedModelsArray(json, "collectionOfSkinnyNestedItems", size, true);

        // Lists
        appendKeyValueArray(json, "listOfIntegerObjects", false, true, "0", "1", "2");
        appendKeyValueArray(json, "listOfStrings", true, true, "0", "1", "2");
        appendKeyNestedModelsArray(json, "listOfSkinnyNestedItems", size, true);

        // Sets
        appendKeyValueArray(json, "setOfIntegerObjects", false, true, "0", "1", "2");
        appendKeyValueArray(json, "setOfStrings", true, true, "0", "1", "2");
        appendKeyNestedModelsArray(json, "setOfSkinnyNestedItems", size, false);

        json.append("}");
        return json.toString();
    }


    private static void appendKeyNestedModelsArray(StringBuilder json, String key, int size, boolean goon)
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


    private static void appendKeyValue(StringBuilder json, String key, String value, boolean quote, boolean goon)
    {
        json.append(key).append(": ");
        if (quote)
        {
            json.append("\"");
        }
        json.append(value);
        if (quote)
        {
            json.append("\"");
        }
        if (goon)
        {
            json.append(", ");
        }
    }


    private static void appendKeyValueArray(StringBuilder json, String key, boolean quote, boolean goon,
            String... values)
    {
        json.append(key).append(": [");
        Iterator<String> iter = Arrays.asList(values).iterator();
        while (iter.hasNext())
        {
            String value = iter.next();
            appendValue(json, value, quote, iter.hasNext());
        }
        json.append("]");
        if (goon)
        {
            json.append(", ");
        }
    }


    private static void appendValue(StringBuilder json, String value, boolean quote, boolean goon)
    {
        if (quote)
        {
            json.append("\"");
        }
        json.append(value);
        if (quote)
        {
            json.append("\"");
        }
        if (goon)
        {
            json.append(", ");
        }
    }
}
