package name.pehl.piriti.client.ferranmaylinch;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;

import com.google.gwt.core.client.GWT;

//@formatter:off
public class PropertyInteger extends Property
{
    public interface PropertyIntegerJsonReader extends JsonReader<PropertyInteger> {}
    public static final PropertyIntegerJsonReader JSON_READER = GWT.create(PropertyIntegerJsonReader.class);

    public interface PropertyIntegerJsonWriter extends JsonWriter<PropertyInteger> {}
    public static final PropertyIntegerJsonWriter JSON_WRITER = GWT.create(PropertyIntegerJsonWriter.class);

    int value;


    //@formatter:on
    public PropertyInteger()
    {
        this(null, 0);
    }


    public PropertyInteger(String name, int value)
    {
        super(name, "integer");
        this.value = value;
    }


    @Override
    public String toString()
    {
        return name + ":int = " + value;
    }
}
