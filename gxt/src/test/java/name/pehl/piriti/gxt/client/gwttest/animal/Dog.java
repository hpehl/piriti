package name.pehl.piriti.gxt.client.gwttest.animal;

import name.pehl.piriti.client.gwttest.animal.Size;
import name.pehl.piriti.gxt.client.json.JsonField;
import name.pehl.piriti.gxt.client.json.JsonModel;
import name.pehl.piriti.gxt.client.json.JsonModelReader;
import name.pehl.piriti.gxt.client.xml.XmlField;
import name.pehl.piriti.gxt.client.xml.XmlModel;
import name.pehl.piriti.gxt.client.xml.XmlModelReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
@JsonModel(@JsonField(property = "size", type = Size.class))
@XmlModel(@XmlField(property = "size", type = Size.class))
public class Dog extends Mammal
{
    public interface DogJsonReader extends JsonModelReader<Dog>
    {
    }

    public static final DogJsonReader JSON = GWT.create(DogJsonReader.class);

    public interface DogXmlReader extends XmlModelReader<Dog>
    {
    }

    public static final DogXmlReader XML = GWT.create(DogXmlReader.class);
}
