package name.pehl.piriti.gxt.client.inheritance;

import name.pehl.piriti.client.inheritance.Size;
import name.pehl.piriti.client.inheritance.SizeConverter;
import name.pehl.piriti.gxt.json.client.Json;
import name.pehl.piriti.gxt.json.client.JsonMappings;
import name.pehl.piriti.gxt.json.client.JsonModelReader;
import name.pehl.piriti.gxt.xml.client.Xml;
import name.pehl.piriti.gxt.xml.client.XmlMappings;
import name.pehl.piriti.gxt.xml.client.XmlModelReader;

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
