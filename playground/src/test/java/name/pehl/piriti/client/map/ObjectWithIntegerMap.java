package name.pehl.piriti.client.map;

import com.google.gwt.core.client.GWT;
import name.pehl.piriti.xml.client.XmlWriter;

import java.util.Map;


public class ObjectWithIntegerMap {

    interface ObjectWithIntegerMapWriter extends XmlWriter<ObjectWithIntegerMap> {
    }

    public static final ObjectWithIntegerMapWriter XML = GWT.create(ObjectWithIntegerMapWriter.class);

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
