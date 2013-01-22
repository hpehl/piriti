package name.pehl.piriti.client.map;

import com.google.gwt.core.client.GWT;
import name.pehl.piriti.xml.client.XmlWriter;

import java.util.Map;

public class ObjectWithMap {
    interface ObjectWithMapWriter extends XmlWriter<ObjectWithMap> {
    }

    public static final ObjectWithMapWriter XML = GWT.create(ObjectWithMapWriter.class);

    private Map<String, String> map;

    ObjectWithMap() {
    }

    public ObjectWithMap(Map<String, String> map) {
        this.map = map;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
