package name.pehl.piriti.gxt.client.gwttest.animal;

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
@JsonFields(@JsonField(name = "color", type = String.class))
@XmlFields(@XmlField(name = "color", type = String.class))
public class Cat extends Mammal
{
    public interface CatJsonReader extends JsonModelReader<Cat>
    {
    }

    public static final CatJsonReader JSON = GWT.create(CatJsonReader.class);

    public interface CatXmlReader extends XmlModelReader<Cat>
    {
    }

    public static final CatXmlReader XML = GWT.create(CatXmlReader.class);
}
