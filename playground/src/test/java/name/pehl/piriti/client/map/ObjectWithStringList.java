package name.pehl.piriti.client.map;

import com.google.gwt.core.client.GWT;
import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import java.util.List;

public class ObjectWithStringList
{
    interface ObjectWithStringListWriter extends XmlWriter<ObjectWithStringList>
    {
    }

    interface ObjectWithStringListReader extends XmlReader<ObjectWithStringList>
    {
    }

    interface ObjectWithStringListJsonWriter extends JsonWriter<ObjectWithStringList>
    {
    }

    interface ObjectWithStringListJsonReader extends JsonReader<ObjectWithStringList>
    {
    }

    public static final ObjectWithStringListWriter XML_WRITER = GWT.create(ObjectWithStringListWriter.class);
    public static final ObjectWithStringListReader XML_READER = GWT.create(ObjectWithStringListReader.class);
    public static final ObjectWithStringListJsonWriter JSON_WRITER = GWT.create(ObjectWithStringListJsonWriter.class);
    public static final ObjectWithStringListJsonReader JSON_READER = GWT.create(ObjectWithStringListJsonReader.class);

    @Path("elements")
    private List<String> list;

    ObjectWithStringList()
    {
    }

    public ObjectWithStringList(List<String> list)
    {
        this.list = list;
    }

    public List<String> getList()
    {
        return list;
    }

    public void setList(List<String> list)
    {
        this.list = list;
    }
}
