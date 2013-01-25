package name.pehl.piriti.client.map;

import com.google.gwt.core.client.GWT;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import java.util.Map;


public class ObjectWithPojoMap {
    interface ObjectWithPojoMapWriter extends XmlWriter<ObjectWithPojoMap> {
    }

    interface ObjectWithPojoMapReader extends XmlReader<ObjectWithPojoMap> {
    }

    public static final ObjectWithPojoMapWriter XML_WRITER = GWT.create(ObjectWithPojoMapWriter.class);
    public static final ObjectWithPojoMapReader XML_READER = GWT.create(ObjectWithPojoMapReader.class);

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
