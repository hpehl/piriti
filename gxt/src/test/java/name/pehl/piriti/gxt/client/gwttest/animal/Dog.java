package name.pehl.piriti.gxt.client.gwttest.animal;

import name.pehl.piriti.client.gwttest.animal.Size;
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
@JsonFields(@JsonField(name = "size", type = Size.class))
@XmlFields(@XmlField(name = "size", type = Size.class))
public class Dog extends Mammal
{
    // --------------------------------------------------- json reader / writer
    
    public interface DogJsonReader extends JsonModelReader<Dog>
    {
    }

    public static final DogJsonReader JSON_READER = GWT.create(DogJsonReader.class);

    // ---------------------------------------------------- xml reader / writer
    
    public interface DogXmlReader extends XmlModelReader<Dog>
    {
    }

    public static final DogXmlReader XML_READER = GWT.create(DogXmlReader.class);
}
