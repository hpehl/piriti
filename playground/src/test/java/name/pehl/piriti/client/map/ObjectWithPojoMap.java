package name.pehl.piriti.client.map;

import com.google.gwt.core.client.GWT;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import java.util.Map;


public class ObjectWithPojoMap implements ObjectWithMap<PojoObject, PojoObject> {
    interface ObjectWithPojoMapWriter extends XmlWriter<ObjectWithPojoMap> {
    }

    interface ObjectWithPojoMapReader extends XmlReader<ObjectWithPojoMap> {
    }

    interface ObjectWithPojoMapJsonWriter extends JsonWriter<ObjectWithPojoMap> {
    }

    interface ObjectWithPojoMapJsonReader extends JsonReader<ObjectWithPojoMap> {
    }

    public static final ObjectWithPojoMapWriter XML_WRITER = GWT.create(ObjectWithPojoMapWriter.class);
    public static final ObjectWithPojoMapReader XML_READER = GWT.create(ObjectWithPojoMapReader.class);
    public static final ObjectWithPojoMapJsonWriter JSON_WRITER = GWT.create(ObjectWithPojoMapJsonWriter.class);
    public static final ObjectWithPojoMapJsonReader JSON_READER = GWT.create(ObjectWithPojoMapJsonReader.class);

    private Map<PojoObject, PojoObject> map;

    ObjectWithPojoMap() {
    }

    public ObjectWithPojoMap(Map<PojoObject, PojoObject> map) {
        this.map = map;
    }

    public Map<PojoObject, PojoObject> getMap() {
        return map;
    }

    public void setMap(Map<PojoObject, PojoObject> map) {
        this.map = map;
    }
}
