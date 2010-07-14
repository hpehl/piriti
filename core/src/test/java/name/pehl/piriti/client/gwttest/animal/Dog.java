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
public class Dog extends Mammal
{
    public interface DogJsonReader extends JsonReader<Dog>
    {
    }

    public static final DogJsonReader JSON = GWT.create(DogJsonReader.class);

    public interface DogXmlReader extends XmlReader<Dog>
    {
    }

    public static final DogXmlReader XML = GWT.create(DogXmlReader.class);

    @JsonField
    @XmlField
    Size size;
}
