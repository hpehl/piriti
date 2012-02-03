package name.pehl.piriti.client.types;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

//@formatter:off
public class Nulls
{
    public interface NullsJsonReader extends JsonReader<Nulls> {}
    public static final NullsJsonReader JSON_READER = GWT.create(NullsJsonReader.class);

    public interface NullsJsonWriter extends JsonWriter<Nulls> {}
    public static final NullsJsonWriter JSON_WRITER = GWT.create(NullsJsonWriter.class);

    public interface NullsXmlReader extends XmlReader<Nulls> {}
    public static final NullsXmlReader XML_READER = GWT.create(NullsXmlReader.class);

    public interface NullsXmlWriter extends XmlWriter<Nulls> {}
    public static final NullsXmlWriter XML_WRITER = GWT.create(NullsXmlWriter.class);
    
    String stringA;
    String stringB;
    Integer integerA;
    Integer integerB;
    Double doubleA;
    Double doubleB;
    
    public Nulls()
    {
        super();
    }

    public Nulls(String stringA, String stringB, Integer integerA, Integer integerB, Double doubleA, Double doubleB)
    {
        super();
        this.stringA = stringA;
        this.stringB = stringB;
        this.integerA = integerA;
        this.integerB = integerB;
        this.doubleA = doubleA;
        this.doubleB = doubleB;
    }
}
