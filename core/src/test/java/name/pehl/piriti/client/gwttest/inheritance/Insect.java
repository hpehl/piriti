package name.pehl.piriti.client.gwttest.inheritance;

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
public class Insect extends Animal
{
    // --------------------------------------------------- json reader / writer

    public interface InsectJsonReader extends JsonReader<Insect> {}
    public static final InsectJsonReader JSON_READER = GWT.create(InsectJsonReader.class);

    public interface InsectJsonWriter extends JsonWriter<Insect> {}
    public static final InsectJsonWriter JSON_WRITER = GWT.create(InsectJsonWriter.class);

    // ---------------------------------------------------- xml reader / writer

    public interface InsectXmlReader extends XmlReader<Insect> {}
    public static final InsectXmlReader XML_READER = GWT.create(InsectXmlReader.class);

    public interface InsectXmlWriter extends XmlWriter<Insect> {}
    public static final InsectXmlWriter XML_WRITER = GWT.create(InsectXmlWriter.class);

    // ------------------------------------------------------------------- data

    @Json @Xml boolean flies;
}
