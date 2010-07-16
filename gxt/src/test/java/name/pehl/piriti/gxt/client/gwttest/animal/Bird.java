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
 * @version $Date$ $Revision$
 */
@JsonFields(@JsonField(name = "wingspan", type = Double.class))
@XmlFields(@XmlField(name = "wingspan", type = Double.class))
public class Bird extends Animal
{
    public interface BirdJsonReader extends JsonModelReader<Bird>
    {
    }

    public static final BirdJsonReader JSON = GWT.create(BirdJsonReader.class);

    public interface BirdXmlReader extends XmlModelReader<Bird>
    {
    }

    public static final BirdXmlReader XML = GWT.create(BirdXmlReader.class);
}
