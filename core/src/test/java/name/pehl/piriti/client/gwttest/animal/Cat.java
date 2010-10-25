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
// @formatter:off
public class Cat extends Mammal
{
    // --------------------------------------------------- json reader / writer

    public interface CatJsonReader extends JsonReader<Cat> {}
    public static final CatJsonReader JSON_READER = GWT.create(CatJsonReader.class);

    public interface CatJsonWriter extends JsonWriter<Cat> {}
    public static final CatJsonWriter JSON_WRITER = GWT.create(CatJsonWriter.class);

    // ---------------------------------------------------- xml reader / writer

    public interface CatXmlReader extends XmlReader<Cat> {}
    public static final CatXmlReader XML_READER = GWT.create(CatXmlReader.class);

    public interface CatXmlWriter extends XmlWriter<Cat> {}
    public static final CatXmlWriter XML_WRITER = GWT.create(CatXmlWriter.class);

    // ------------------------------------------------------------------- data

    @Json @Xml String color;
}
