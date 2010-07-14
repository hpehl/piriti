package name.pehl.piriti.client.gwttest.animal;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class Bird extends Animal
{
    public interface BirdJsonReader extends JsonReader<Bird>
    {
    }

    public static final BirdJsonReader JSON = GWT.create(BirdJsonReader.class);

    public interface BirdXmlReader extends XmlReader<Bird>
    {
    }

    public static final BirdXmlReader XML = GWT.create(BirdXmlReader.class);

    @JsonField
    @XmlField
    double wingspan;
}
