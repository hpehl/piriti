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
public class Insect extends Animal
{
    // --------------------------------------------------- json reader / writer

    // @formatter:off
    public interface InsectJsonReader extends JsonReader<Insect> {}
    public static final InsectJsonReader JSON_READER = GWT.create(InsectJsonReader.class);

    public interface InsectJsonWriter extends JsonWriter<Insect> {}
    public static final InsectJsonWriter JSON_WRITER = GWT.create(InsectJsonWriter.class);
    // @formatter:on

    // ---------------------------------------------------- xml reader / writer

    // @formatter:off
    public interface InsectXmlReader extends XmlReader<Insect> {}
    public static final InsectXmlReader XML_READER = GWT.create(InsectXmlReader.class);

    public interface InsectXmlWriter extends XmlWriter<Insect> {}
    public static final InsectXmlWriter XML_WRITER = GWT.create(InsectXmlWriter.class);
    // @formatter:on

    // ------------------------------------------------------------------- data

    @Json
    @Xml
    boolean flies;
}
