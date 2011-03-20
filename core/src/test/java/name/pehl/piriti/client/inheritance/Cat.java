package name.pehl.piriti.client.inheritance;

import name.pehl.piriti.json.client.Json;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.Xml;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

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
