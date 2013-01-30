package name.pehl.piriti.client.map;

import com.google.gwt.core.client.GWT;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import java.util.Map;


public class ObjectWithIntegerMap implements ObjectWithMap<Integer, String> {
    interface ObjectWithIntegerMapXmlWriter extends XmlWriter<ObjectWithIntegerMap> {
    }

    interface ObjectWithIntegerMapXmlReader extends XmlReader<ObjectWithIntegerMap> {
    }

    interface ObjectWithIntegerMapJsonWriter extends JsonWriter<ObjectWithIntegerMap> {
    }

    interface ObjectWithIntegerMapJsonReader extends JsonReader<ObjectWithIntegerMap> {
    }

    public static final ObjectWithIntegerMapXmlWriter XML_WRITER = GWT.create(ObjectWithIntegerMapXmlWriter.class);
    public static final ObjectWithIntegerMapXmlReader XML_READER = GWT.create(ObjectWithIntegerMapXmlReader.class);
    public static final ObjectWithIntegerMapJsonWriter JSON_WRITER = GWT.create(ObjectWithIntegerMapJsonWriter.class);
    public static final ObjectWithIntegerMapJsonReader JSON_READER = GWT.create(ObjectWithIntegerMapJsonReader.class);

    private Map<Integer, String> map;

    ObjectWithIntegerMap() {
    }

    public ObjectWithIntegerMap(Map<Integer, String> map) {
        this.map = map;
    }

    public Map<Integer, String> getMap() {
        return map;
    }

    public void setMap(Map<Integer, String> map) {
        this.map = map;
    }
}
