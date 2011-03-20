package name.pehl.piriti.gxt.client.inheritance;

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
@JsonMappings(@Json(property = "color", type = String.class))
@XmlMappings(@Xml(property = "color", type = String.class))
public class Cat extends Mammal
{
    // --------------------------------------------------- json reader / writer
    
    public interface CatJsonReader extends JsonModelReader<Cat>
    {
    }

    public static final CatJsonReader JSON_READER = GWT.create(CatJsonReader.class);

    // ---------------------------------------------------- xml reader / writer
    
    public interface CatXmlReader extends XmlModelReader<Cat>
    {
    }

    public static final CatXmlReader XML_READER = GWT.create(CatXmlReader.class);
}
