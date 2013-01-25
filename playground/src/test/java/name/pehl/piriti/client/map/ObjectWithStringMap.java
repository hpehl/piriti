package name.pehl.piriti.client.map;

import com.google.gwt.core.client.GWT;
import name.pehl.piriti.xml.client.XmlWriter;

import java.util.Map;

public class ObjectWithStringMap {
    interface ObjectWithStringMapWriter extends XmlWriter<ObjectWithStringMap> {
    }

    public static final ObjectWithStringMapWriter XML = GWT.create(ObjectWithStringMapWriter.class);

    private Map<String, String> map;

    ObjectWithStringMap() {
    }

    public ObjectWithStringMap(Map<String, String> map) {
        this.map = map;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
