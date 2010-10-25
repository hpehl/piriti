package name.pehl.piriti.gxt.client.gwttest.animal;

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
@JsonMappings(@Json(property = "wingspan", type = Integer.class))
@XmlMappings(@Xml(property = "wingspan", type = Integer.class))
public class Bird extends Animal
{
    // --------------------------------------------------- json reader / writer
    
    public interface BirdJsonReader extends JsonModelReader<Bird>
    {
    }

    public static final BirdJsonReader JSON_READER = GWT.create(BirdJsonReader.class);

    // ---------------------------------------------------- xml reader / writer
    
    public interface BirdXmlReader extends XmlModelReader<Bird>
    {
    }

    public static final BirdXmlReader XML_READER = GWT.create(BirdXmlReader.class);
}
