package name.pehl.piriti.client.map;

import com.google.gwt.core.client.GWT;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import java.util.Map;

public class ObjectWithStringMap implements ObjectWithMap<String, String>
{
    interface ObjectWithStringMapWriter extends XmlWriter<ObjectWithStringMap>
    {
    }

    interface ObjectWithStringMapReader extends XmlReader<ObjectWithStringMap>
    {
    }

    interface ObjectWithStringMapJsonWriter extends JsonWriter<ObjectWithStringMap>
    {
    }

    interface ObjectWithStringMapJsonReader extends JsonReader<ObjectWithStringMap>
    {
    }

    public static final ObjectWithStringMapWriter XML_WRITER = GWT.create(ObjectWithStringMapWriter.class);
    public static final ObjectWithStringMapReader XML_READER = GWT.create(ObjectWithStringMapReader.class);
    public static final ObjectWithStringMapJsonWriter JSON_WRITER = GWT.create(ObjectWithStringMapJsonWriter.class);
    public static final ObjectWithStringMapJsonReader JSON_READER = GWT.create(ObjectWithStringMapJsonReader.class);

    private Map<String, String> map;

    ObjectWithStringMap()
    {
    }

    public ObjectWithStringMap(Map<String, String> map)
    {
        this.map = map;
    }

    public Map<String, String> getMap()
    {
        return map;
    }

    public void setMap(Map<String, String> map)
    {
        this.map = map;
    }
}
