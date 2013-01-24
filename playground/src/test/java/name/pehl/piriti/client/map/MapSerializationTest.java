package name.pehl.piriti.client.map;

import name.pehl.piriti.client.AbstractPlaygroundTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapSerializationTest extends AbstractPlaygroundTest {
    @Test
    public void testXmlMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        String xml = ObjectWithMap.XML.toXml(new ObjectWithMap(map));
        System.out.println(xml);
    }

    @Test
    public void testXmlList() {
        String xml = ObjectWithList.XML.toXml(new ObjectWithList());
        System.out.println(xml);
    }
}
