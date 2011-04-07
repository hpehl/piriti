package name.pehl.piriti.client.inheritance;

import name.pehl.piriti.converter.client.Convert;
import name.pehl.piriti.json.client.JsonReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 686
 *          $
 */
// @formatter:off
public class Dog extends Mammal
{
    // --------------------------------------------------- json reader / writer

    public interface DogJsonReader extends JsonReader<Dog> {}
    public static final DogJsonReader JSON_READER = GWT.create(DogJsonReader.class);

//    public interface DogJsonWriter extends JsonWriter<Dog> {}
//    public static final DogJsonWriter JSON_WRITER = GWT.create(DogJsonWriter.class);

    // ---------------------------------------------------- xml reader / writer

//    public interface DogXmlReader extends XmlReader<Dog> {}
//    public static final DogXmlReader XML_READER = GWT.create(DogXmlReader.class);
//
//    public interface DogXmlWriter extends XmlWriter<Dog> {}
//    public static final DogXmlWriter XML_WRITER = GWT.create(DogXmlWriter.class);

    // ------------------------------------------------------------------- data

    @Convert(SizeConverter.class) Size size;
}
