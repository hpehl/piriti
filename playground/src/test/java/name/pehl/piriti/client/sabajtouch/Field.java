package name.pehl.piriti.client.sabajtouch;

import name.pehl.piriti.xml.client.XmlReader;

import com.google.gwt.core.client.GWT;

public class Field
{
    interface FieldReader extends XmlReader<Field>
    {
    }

    public static final FieldReader XML = GWT.create(FieldReader.class);

    public enum FieldType
    {
        STRING,
        INT
    }

    String id;


    public String getId()
    {
        return id;
    }


    public void setId(String id)
    {
        this.id = id;
    }


    @Override
    public String toString()
    {
        return "Field [id=" + id + "]";
    }
}
