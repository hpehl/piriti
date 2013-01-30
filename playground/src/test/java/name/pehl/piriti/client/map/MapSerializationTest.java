package name.pehl.piriti.client.map;

import com.google.gwt.user.client.Random;
import name.pehl.piriti.client.AbstractPlaygroundTest;
import org.junit.Test;

import java.util.*;

public class MapSerializationTest extends AbstractPlaygroundTest
{
    @Test
    public void testXmlMapString()
    {
        Map<String, String> map = new HashMap<String, String>();
        String key1 = "key1";
        String value1 = "value1";
        map.put(key1, value1);

        String key2 = "key2";
        String value2 = "value2";
        map.put(key2, value2);

        String key3 = null;
        String value3 = "value3";
        map.put(key3, value3);

        String xml = ObjectWithStringMap.XML_WRITER.toXml(new ObjectWithStringMap(map));
        ObjectWithStringMap result = ObjectWithStringMap.XML_READER.read(xml);

        assertBackAndForth(key1, value1, key2, value2, key3, value3, result);
    }

    @Test
    public void testJsonMapString()
    {
        Map<String, String> map = new HashMap<String, String>();
        String key1 = "key1";
        String value1 = "value1";
        map.put(key1, value1);

        String key2 = "key2";
        String value2 = "value2";
        map.put(key2, value2);

        String key3 = null;
        String value3 = "value3";
        map.put(key3, value3);

        String json = ObjectWithStringMap.JSON_WRITER.toJson(new ObjectWithStringMap(map));
        ObjectWithStringMap result = ObjectWithStringMap.JSON_READER.read(json);

        assertBackAndForth(key1, value1, key2, value2, key3, value3, result);
    }

    @Test
    public void testXmlMapInteger()
    {
        Integer key1 = 1;
        String value1 = "value1";
        Integer key2 = 2;
        String value2 = "value2";
        Integer key3 = null;
        String value3 = "value3";

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);

        String xml = ObjectWithIntegerMap.XML_WRITER.toXml(new ObjectWithIntegerMap(map));
        ObjectWithIntegerMap result = ObjectWithIntegerMap.XML_READER.read(xml);

        assertBackAndForth(key1, value1, key2, value2, key3, value3, result);
    }

    @Test
    public void testXmlMapEnum()
    {
        EnumObject key1 = EnumObject.VALUE1;
        String value1 = "value1";
        EnumObject key2 = EnumObject.VALUE2;
        String value2 = "value2";
        EnumObject key3 = null;
        String value3 = "value3";

        Map<EnumObject, String> map = new HashMap<EnumObject, String>();
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);

        String xml = ObjectWithEnumMap.XML_WRITER.toXml(new ObjectWithEnumMap(map));
        ObjectWithEnumMap result = ObjectWithEnumMap.XML_READER.read(xml);

        assertBackAndForth(key1, value1, key2, value2, key3, value3, result);
    }

    @Test
    public void testJsonMapEnum()
    {
        EnumObject key1 = EnumObject.VALUE1;
        String value1 = "value1";
        EnumObject key2 = EnumObject.VALUE2;
        String value2 = "value2";
        EnumObject key3 = null;
        String value3 = "value3";

        Map<EnumObject, String> map = new HashMap<EnumObject, String>();
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);

        String json = ObjectWithEnumMap.JSON_WRITER.toJson(new ObjectWithEnumMap(map));
        ObjectWithEnumMap result = ObjectWithEnumMap.JSON_READER.read(json);

        assertBackAndForth(key1, value1, key2, value2, key3, value3, result);
    }

    @Test
    public void testJsonMapInteger()
    {
        Integer key1 = 1;
        String value1 = "value1";
        Integer key2 = 2;
        String value2 = "value2";
        Integer key3 = null;
        String value3 = "value3";

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);

        String json = ObjectWithIntegerMap.JSON_WRITER.toJson(new ObjectWithIntegerMap(map));
        ObjectWithIntegerMap result = ObjectWithIntegerMap.JSON_READER.read(json);

        assertBackAndForth(key1, value1, key2, value2, key3, value3, result);
    }

    @Test
    public void testXmlPojoMap()
    {
        Map<PojoObject, PojoObject> map = new HashMap<PojoObject, PojoObject>();
        PojoObject key1 = makePojo(1);
        PojoObject value1 = makePojo(1);
        map.put(key1, value1);

        PojoObject key2 = makePojo(2);
        PojoObject value2 = makePojo(2);
        map.put(key2, value2);

        PojoObject key3 = null;
        PojoObject value3 = makePojo(3);
        map.put(key3, value3);

        String xml = ObjectWithPojoMap.XML_WRITER.toXml(new ObjectWithPojoMap(map));
        ObjectWithPojoMap result = ObjectWithPojoMap.XML_READER.read(xml);

        assertBackAndForth(key1, value1, key2, value2, key3, value3, result);
    }

    @Test
    public void testJsonPojoMap()
    {
        Map<PojoObject, PojoObject> map = new HashMap<PojoObject, PojoObject>();
        PojoObject key1 = makePojo(1);
        PojoObject value1 = makePojo(1);
        map.put(key1, value1);

        StringBuilder b;
        PojoObject key2 = makePojo(2);
        PojoObject value2 = makePojo(2);
        map.put(key2, value2);

        PojoObject key3 = null;
        PojoObject value3 = makePojo(3);
        map.put(key3, value3);

        String json = ObjectWithPojoMap.JSON_WRITER.toJson(new ObjectWithPojoMap(map));
        ObjectWithPojoMap result = ObjectWithPojoMap.JSON_READER.read(json);

        assertBackAndForth(key1, value1, key2, value2, key3, value3, result);
    }

    @Test
    public void testXmlStringList()
    {
        String object1 = "value1";
        String object2 = "value2";
        String object3 = "value3";

        List<String> values = new ArrayList<String>();
        values.add(object1);
        values.add(object2);
        values.add(object3);

        String xml = ObjectWithList.XML_WRITER.toXml(new ObjectWithList(values));
        ObjectWithList result = ObjectWithList.XML_READER.read(xml);
        String resultXml = ObjectWithList.XML_WRITER.toXml(result);

        assertTrue(result.getList().contains(object1));
        assertTrue(result.getList().contains(object2));
        assertTrue(result.getList().contains(object3));
        assertEquals(xml, resultXml);
    }

    @Test
    public void testJsonStringList()
    {
        String object1 = "value1";
        String object2 = "value2";
        String object3 = "value3";

        List<String> values = new ArrayList<String>();
        values.add(object1);
        values.add(object2);
        values.add(object3);

        String json = ObjectWithList.JSON_WRITER.toJson(new ObjectWithList(values));
        ObjectWithList result = ObjectWithList.JSON_READER.read(json);
        String resultJson = ObjectWithList.JSON_WRITER.toJson(result);

        assertTrue(result.getList().contains(object1));
        assertTrue(result.getList().contains(object2));
        assertTrue(result.getList().contains(object3));
        assertEquals(json, resultJson);
    }

    private PojoObject makePojo(int id)
    {
        return new PojoObject(Arrays.asList(Random.nextInt(), Random.nextInt(), Random.nextInt()),
                String.valueOf(id), Random.nextBoolean());
    }

    private <K, V> void assertBackAndForth(K key1, V value1, K key2, V value2, K key3, V value3, ObjectWithMap<K,
            V> result)
    {
        assertTrue(result.getMap().containsKey(key1));
        assertTrue(result.getMap().containsKey(key2));
        assertTrue(result.getMap().containsKey(key3));
        assertTrue(result.getMap().containsValue(value1));
        assertTrue(result.getMap().containsValue(value2));
        assertTrue(result.getMap().containsValue(value3));
    }
}
