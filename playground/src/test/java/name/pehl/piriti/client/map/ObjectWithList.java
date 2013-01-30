package name.pehl.piriti.client.map;

import com.google.gwt.core.client.GWT;
import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import java.util.List;

public class ObjectWithList
{
    interface ObjectWithListWriter extends XmlWriter<ObjectWithList>
    {
    }

    interface ObjectWithListReader extends XmlReader<ObjectWithList>
    {
    }

    interface ObjectWithListJsonWriter extends JsonWriter<ObjectWithList>
    {
    }

    interface ObjectWithListJsonReader extends JsonReader<ObjectWithList>
    {
    }

    public static final ObjectWithListWriter XML_WRITER = GWT.create(ObjectWithListWriter.class);
    public static final ObjectWithListReader XML_READER = GWT.create(ObjectWithListReader.class);
    public static final ObjectWithListJsonWriter JSON_WRITER = GWT.create(ObjectWithListJsonWriter.class);
    public static final ObjectWithListJsonReader JSON_READER = GWT.create(ObjectWithListJsonReader.class);

    @Path("elements")
    private List<String> list;

    ObjectWithList()
    {
    }

    public ObjectWithList(List<String> list)
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
