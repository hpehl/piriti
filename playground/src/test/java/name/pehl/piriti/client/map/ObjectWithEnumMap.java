package name.pehl.piriti.client.map;

import com.google.gwt.core.client.GWT;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import java.util.Map;


public class ObjectWithEnumMap implements ObjectWithMap<EnumObject, String> {
    interface ObjectWithEnumMapXmlWriter extends XmlWriter<ObjectWithEnumMap> {
    }

    interface ObjectWithEnumMapXmlReader extends XmlReader<ObjectWithEnumMap> {
    }

    interface ObjectWithEnumMapJsonWriter extends JsonWriter<ObjectWithEnumMap> {
    }

    interface ObjectWithEnumMapJsonReader extends JsonReader<ObjectWithEnumMap> {
    }

    public static final ObjectWithEnumMapXmlWriter XML_WRITER = GWT.create(ObjectWithEnumMapXmlWriter.class);
    public static final ObjectWithEnumMapXmlReader XML_READER = GWT.create(ObjectWithEnumMapXmlReader.class);
    public static final ObjectWithEnumMapJsonWriter JSON_WRITER = GWT.create(ObjectWithEnumMapJsonWriter.class);
    public static final ObjectWithEnumMapJsonReader JSON_READER = GWT.create(ObjectWithEnumMapJsonReader.class);

    private Map<EnumObject, String> map;

    ObjectWithEnumMap() {
    }

    public ObjectWithEnumMap(Map<EnumObject, String> map) {
        this.map = map;
    }

    public Map<EnumObject, String> getMap() {
        return map;
    }

    public void setMap(Map<EnumObject, String> map) {
        this.map = map;
    }
}
