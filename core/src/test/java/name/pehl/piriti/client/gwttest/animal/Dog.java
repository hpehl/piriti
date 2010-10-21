package name.pehl.piriti.client.gwttest.animal;

import name.pehl.piriti.client.json.Json;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.json.JsonWriter;
import name.pehl.piriti.client.xml.Xml;
import name.pehl.piriti.client.xml.XmlReader;
import name.pehl.piriti.client.xml.XmlWriter;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 686
 *          $
 */
public class Dog extends Mammal
{
    // --------------------------------------------------- json reader / writer

    // @formatter:off
    public interface DogJsonReader extends JsonReader<Dog> {}
    public static final DogJsonReader JSON_READER = GWT.create(DogJsonReader.class);

    public interface DogJsonWriter extends JsonWriter<Dog> {}
    public static final DogJsonWriter JSON_WRITER = GWT.create(DogJsonWriter.class);
    // @formatter:on

    // ---------------------------------------------------- xml reader / writer

    // @formatter:off
    public interface DogXmlReader extends XmlReader<Dog> {}
    public static final DogXmlReader XML_READER = GWT.create(DogXmlReader.class);

    public interface DogXmlWriter extends XmlWriter<Dog> {}
    public static final DogXmlWriter XML_WRITER = GWT.create(DogXmlWriter.class);
    // @formatter:on

    // ------------------------------------------------------------------- data

    @Json
    @Xml
    Size size;
}
