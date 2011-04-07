package name.pehl.piriti.client.inheritance;

import name.pehl.piriti.json.client.JsonReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 686
 *          $
 */
// @formatter:off
public class Bird extends Animal
{
    // --------------------------------------------------- json reader / writer

    public interface BirdJsonReader extends JsonReader<Bird> {}
    public static final BirdJsonReader JSON_READER = GWT.create(BirdJsonReader.class);

//    public interface BirdJsonWriter extends JsonWriter<Bird> {}
//    public static final BirdJsonWriter JSON_WRITER = GWT.create(BirdJsonWriter.class);

    // ---------------------------------------------------- xml reader / writer

//    public interface BirdXmlReader extends XmlReader<Bird> {}
//    public static final BirdXmlReader XML_READER = GWT.create(BirdXmlReader.class);
//
//    public interface BirdXmlWriter extends XmlWriter<Bird> { }
//    public static final BirdXmlWriter XML_WRITER = GWT.create(BirdXmlWriter.class);

    // ------------------------------------------------------------------- data

    int wingspan;
}
