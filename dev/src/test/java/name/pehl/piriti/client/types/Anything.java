package name.pehl.piriti.client.types;

import java.util.List;

import name.pehl.piriti.commons.client.Mapping;
import name.pehl.piriti.commons.client.Mappings;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

//@formatter:off
public class Anything
{
    public interface AnythingJsonReader extends JsonReader<Anything> {}
    public static final AnythingJsonReader JSON_READER = GWT.create(AnythingJsonReader.class);

    public interface AnythingJsonWriter extends JsonWriter<Anything> {}
    public static final AnythingJsonWriter JSON_WRITER = GWT.create(AnythingJsonWriter.class);


    @Mappings(@Mapping(value = "things", path = "things/thing"))
    public interface AnythingXmlReader extends XmlReader<Anything> {}
    public static final AnythingXmlReader XML_READER = GWT.create(AnythingXmlReader.class);

    public interface AnythingXmlWriter extends XmlWriter<Anything> {}
    public static final AnythingXmlWriter XML_WRITER = GWT.create(AnythingXmlWriter.class);
    
    
    Object thing;
    List<Object> things;
}
