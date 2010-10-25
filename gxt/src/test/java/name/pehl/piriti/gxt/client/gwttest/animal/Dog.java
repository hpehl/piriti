package name.pehl.piriti.gxt.client.gwttest.animal;

import name.pehl.piriti.client.gwttest.animal.Size;
import name.pehl.piriti.client.gwttest.animal.SizeConverter;
import name.pehl.piriti.gxt.client.json.Json;
import name.pehl.piriti.gxt.client.json.JsonMappings;
import name.pehl.piriti.gxt.client.json.JsonModelReader;
import name.pehl.piriti.gxt.client.xml.Xml;
import name.pehl.piriti.gxt.client.xml.XmlMappings;
import name.pehl.piriti.gxt.client.xml.XmlModelReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision$
 */
@JsonMappings(@Json(property = "size", type = Size.class, converter = SizeConverter.class))
@XmlMappings(@Xml(property = "size", type = Size.class, converter = SizeConverter.class))
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
