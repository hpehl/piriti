package name.pehl.piriti.client.map;

import com.google.gwt.core.client.GWT;
import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.xml.client.XmlWriter;

import java.util.ArrayList;
import java.util.List;

public class ObjectWithList{
    interface ObjectWithMapWriter extends XmlWriter<ObjectWithList> {
    }

    public static final ObjectWithMapWriter XML = GWT.create(ObjectWithMapWriter.class);

    @Path("elements")
    private List<String> list = new ArrayList<String>();

    public ObjectWithList() {
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

}
