package name.pehl.piriti.client.ferranmaylinch;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;

import com.google.gwt.core.client.GWT;

//@formatter:off
public class PropertyString extends Property
{
    public interface PropertyStringJsonReader extends JsonReader<PropertyString> {}
    public static final PropertyStringJsonReader JSON_READER = GWT.create(PropertyStringJsonReader.class);

    public interface PropertyStringJsonWriter extends JsonWriter<PropertyString> {}
    public static final PropertyStringJsonWriter JSON_WRITER = GWT.create(PropertyStringJsonWriter.class);

    String value;


    //@formatter:on
    public PropertyString()
    {
        this(null, null);
    }


    public PropertyString(String name, String value)
    {
        super(name, "string");
        this.value = value;
    }


    @Override
    public String toString()
    {
        return name + ":String = \"" + value + "\"";
    }
}
