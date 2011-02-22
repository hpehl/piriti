package name.pehl.piriti.gxt.client.gwttest.inheritance;

import name.pehl.piriti.gxt.client.json.Json;
import name.pehl.piriti.gxt.client.json.JsonMappings;
import name.pehl.piriti.gxt.client.json.JsonModelReader;
import name.pehl.piriti.gxt.client.xml.Xml;
import name.pehl.piriti.gxt.client.xml.XmlMappings;
import name.pehl.piriti.gxt.client.xml.XmlModelReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 740
 *          $
 */
@JsonMappings(@Json(property = "flies", type = Boolean.class))
@XmlMappings(@Xml(property = "flies", type = Boolean.class))
public class Insect extends Animal
{
    // --------------------------------------------------- json reader / writer

    public interface InsectJsonReader extends JsonModelReader<Insect>
    {
    }

    public static final InsectJsonReader JSON_READER = GWT.create(InsectJsonReader.class);

    // ---------------------------------------------------- xml reader / writer

    public interface InsectXmlReader extends XmlModelReader<Insect>
    {
    }

    public static final InsectXmlReader XML_READER = GWT.create(InsectXmlReader.class);
}
