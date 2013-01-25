package name.pehl.piriti.client.map;

import com.google.gwt.user.client.Random;
import name.pehl.piriti.client.AbstractPlaygroundTest;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapSerializationTest extends AbstractPlaygroundTest {
    @Test
    public void testXmlMapString() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put(null, "value3");
        String xml = ObjectWithStringMap.XML.toXml(new ObjectWithStringMap(map));
        System.out.println(xml);
    }

    @Test
    public void testXmlMapInteger() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "value1");
        map.put(2, "value2");
        map.put(null, "value3");
        String xml = ObjectWithIntegerMap.XML.toXml(new ObjectWithIntegerMap(map));
        System.out.println(xml);
    }

    @Test
    public void testXmlPojoMap() {
        Map<PojoObject, PojoObject> map = new HashMap<PojoObject, PojoObject>();
        map.put(makePojo(1), makePojo(1));
        map.put(makePojo(2), makePojo(2));
        map.put(null, makePojo(3));
        String xml = ObjectWithPojoMap.XML_WRITER.toXml(new ObjectWithPojoMap(map));
        System.out.println(xml);
        ObjectWithPojoMap result = ObjectWithPojoMap.XML_READER.read(xml);
        String resultXml = ObjectWithPojoMap.XML_WRITER.toXml(result);
        System.out.println(resultXml);
        assertEquals(xml, resultXml);
    }

    private PojoObject makePojo(int id) {
        return new PojoObject(Arrays.asList(Random.nextInt(), Random.nextInt(), Random.nextInt()),
                String.valueOf(id), Random.nextBoolean());
    }

    @Test
    public void testXmlList() {
        String xml = ObjectWithList.XML.toXml(new ObjectWithList());
        System.out.println(xml);
    }

    @Test
    public void test() {
        String pojoXml = Result.PojoResult.WRITER.toXml(new Result.PojoResult(makePojo(1)));
        Result<PojoObject> result2 = Result.PojoResult.READER.read(pojoXml);
        System.out.println(result2.getObject());
        System.out.println(pojoXml);
    }
}
