package name.pehl.piriti.client.ferranmaylinch;

import java.util.Date;
import java.util.List;

import name.pehl.piriti.commons.client.CreateWith;
import name.pehl.piriti.commons.client.Format;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;

import com.google.gwt.core.client.GWT;

//@formatter:off
public class Doc
{
    public interface DocJsonReader extends JsonReader<Doc> {}
    public static final DocJsonReader JSON_READER = GWT.create(DocJsonReader.class);

    public interface DocJsonWriter extends JsonWriter<Doc> {}
    public static final DocJsonWriter JSON_WRITER = GWT.create(DocJsonWriter.class);

    
    @Format(Constants.DATE_FORMAT) Date date;
    List<GeneralProperty> generalProperties;
    @CreateWith(PropertyCreator.class) List<Property> properties;


    @Override
    public String toString()
    {
        return date + " " + properties.toString() + " " + generalProperties.toString();
    }
}
