package name.pehl.piriti.client.types;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

//@formatter:off
public class Arrays
{
    public interface ArraysJsonReader extends JsonReader<Arrays> {}
    public static final ArraysJsonReader JSON_READER = GWT.create(ArraysJsonReader.class);

    public interface ArraysJsonWriter extends JsonWriter<Arrays> {}
    public static final ArraysJsonWriter JSON_WRITER = GWT.create(ArraysJsonWriter.class);


    public interface ArraysXmlReader extends XmlReader<Arrays> {}
    public static final ArraysXmlReader XML_READER = GWT.create(ArraysXmlReader.class);

    public interface ArraysXmlWriter extends XmlWriter<Arrays> {}
    public static final ArraysXmlWriter XML_WRITER = GWT.create(ArraysXmlWriter.class);
    
    
    String[] strings;
    String[] mixed;
}
