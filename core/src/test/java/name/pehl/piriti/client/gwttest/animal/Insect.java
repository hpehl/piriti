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
public class Insect extends Animal
{
    public interface InsectJsonReader extends JsonReader<Insect>
    {
    }

    public static final InsectJsonReader JSON = GWT.create(InsectJsonReader.class);

    public interface InsectXmlReader extends XmlReader<Insect>
    {
    }

    public static final InsectXmlReader XML = GWT.create(InsectXmlReader.class);

    @JsonField
    @XmlField
    boolean flies;
}
