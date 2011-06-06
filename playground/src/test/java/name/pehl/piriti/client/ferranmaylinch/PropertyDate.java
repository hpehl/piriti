package name.pehl.piriti.client.ferranmaylinch;

import java.util.Date;

import name.pehl.piriti.commons.client.Format;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;

import com.google.gwt.core.client.GWT;

//@formatter:off
public class PropertyDate extends Property
{
    public interface PropertyDateJsonReader extends JsonReader<PropertyDate> {}
    public static final PropertyDateJsonReader JSON_READER = GWT.create(PropertyDateJsonReader.class);

    public interface PropertyDateJsonWriter extends JsonWriter<PropertyDate> {}
    public static final PropertyDateJsonWriter JSON_WRITER = GWT.create(PropertyDateJsonWriter.class);

    @Format("dd/MM/yyyy") Date value;

    
    //@formatter:on
    public PropertyDate()
    {
        this(null, null);
    }


    public PropertyDate(String name, Date value)
    {
        super(name, "date");
        this.value = value;
    }


    @Override
    public String toString()
    {
        return name + ":Date = " + value;
    }
}
