package name.pehl.piriti.gxt.client.gwttest.animal;

import name.pehl.piriti.gxt.client.json.JsonField;
import name.pehl.piriti.gxt.client.json.JsonFields;
import name.pehl.piriti.gxt.client.json.JsonModelReader;
import name.pehl.piriti.gxt.client.xml.XmlField;
import name.pehl.piriti.gxt.client.xml.XmlFields;
import name.pehl.piriti.gxt.client.xml.XmlModelReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 740
 *          $
 */
@JsonFields(@JsonField(name = "flies", type = Boolean.class))
@XmlFields(@XmlField(name = "flies", type = Boolean.class))
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
