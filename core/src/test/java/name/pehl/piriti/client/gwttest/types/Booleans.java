package name.pehl.piriti.client.gwttest.types;

import name.pehl.piriti.client.json.Json;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.json.JsonWriter;
import name.pehl.piriti.client.xml.Xml;
import name.pehl.piriti.client.xml.XmlReader;
import name.pehl.piriti.client.xml.XmlWriter;

import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
// @formatter:off
public class Booleans
{
    public interface BooleansJsonReader extends JsonReader<Booleans> {}
    public static final BooleansJsonReader JSON_READER = GWT.create(BooleansJsonReader.class);

    public interface BooleansJsonWriter extends JsonWriter<Booleans> {}
    public static final BooleansJsonWriter JSON_WRITER = GWT.create(BooleansJsonWriter.class);


    public interface BooleansXmlReader extends XmlReader<Booleans> {}
    public static final BooleansXmlReader XML_READER = GWT.create(BooleansXmlReader.class);

    public interface BooleansXmlWriter extends XmlWriter<Booleans> {}
    public static final BooleansXmlWriter XML_WRITER = GWT.create(BooleansXmlWriter.class);
    
    
    @Json @Xml private boolean b1;
    @Json @Xml public boolean b2;


    public boolean isB1()
    {
        return b1;
    }


    public void setB1(boolean b1)
    {
        this.b1 = b1;
    }
}
