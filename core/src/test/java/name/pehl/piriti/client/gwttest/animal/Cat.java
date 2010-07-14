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
public class Cat extends Mammal
{
    public interface CatJsonReader extends JsonReader<Cat>
    {
    }
    
    public static final CatJsonReader JSON = GWT.create(CatJsonReader.class);
    
    public interface CatXmlReader extends XmlReader<Cat>
    {
    }

    public static final CatXmlReader XML = GWT.create(CatXmlReader.class);

    
    @JsonField
    @XmlField
    String color;
}
