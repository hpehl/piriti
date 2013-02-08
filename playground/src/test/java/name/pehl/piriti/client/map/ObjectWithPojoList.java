package name.pehl.piriti.client.map;

import com.google.gwt.core.client.GWT;
import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import java.util.List;

public class ObjectWithPojoList
{
    interface ObjectWithPojoListWriter extends XmlWriter<ObjectWithPojoList>
    {
    }

    interface ObjectWithPojoListReader extends XmlReader<ObjectWithPojoList>
    {
    }

    interface ObjectWithPojoListJsonWriter extends JsonWriter<ObjectWithPojoList>
    {
    }

    interface ObjectWithPojoListJsonReader extends JsonReader<ObjectWithPojoList>
    {
    }

    public static final ObjectWithPojoListWriter XML_WRITER = GWT.create(ObjectWithPojoListWriter.class);
    public static final ObjectWithPojoListReader XML_READER = GWT.create(ObjectWithPojoListReader.class);
    public static final ObjectWithPojoListJsonWriter JSON_WRITER = GWT.create(ObjectWithPojoListJsonWriter.class);
    public static final ObjectWithPojoListJsonReader JSON_READER = GWT.create(ObjectWithPojoListJsonReader.class);

    @Path("elements")
    private List<PojoObject> list;

    ObjectWithPojoList()
    {
    }

    public ObjectWithPojoList(List<PojoObject> list)
    {
        this.list = list;
    }

    public List<PojoObject> getList()
    {
        return list;
    }

    public void setList(List<PojoObject> list)
    {
        this.list = list;
    }
}
