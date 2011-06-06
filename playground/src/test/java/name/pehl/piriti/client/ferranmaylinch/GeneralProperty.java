package name.pehl.piriti.client.ferranmaylinch;


import name.pehl.piriti.converter.client.Convert;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;

import com.google.gwt.core.client.GWT;

//@formatter:off
public class GeneralProperty
{
    public interface GeneralPropertyJsonReader extends JsonReader<GeneralProperty> {}
    public static final GeneralPropertyJsonReader JSON_READER = GWT.create(GeneralPropertyJsonReader.class);

    public interface GeneralPropertyJsonWriter extends JsonWriter<GeneralProperty> {}
    public static final GeneralPropertyJsonWriter JSON_WRITER = GWT.create(GeneralPropertyJsonWriter.class);

    String name;
    String type;
    @Convert(GeneralPropertyValueConverter.class) Object value;

    
    //@formatter:on
    public GeneralProperty()
    {
        this(null, null, null);
    }


    public GeneralProperty(String name, String type, Object value)
    {
        this.name = name;
        this.type = type;
        this.value = value;
    }


    @Override
    public String toString()
    {
        return name + ":" + type + " = " + value;
    }
}
