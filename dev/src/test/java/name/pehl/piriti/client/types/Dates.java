package name.pehl.piriti.client.types;

import java.util.Date;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
// @formatter:off
public class Dates
{
    public interface DatesJsonReader extends JsonReader<Dates> {}
    public static final DatesJsonReader JSON_READER = GWT.create(DatesJsonReader.class);

    public interface DatesJsonWriter extends JsonWriter<Dates> {}
    public static final DatesJsonWriter JSON_WRITER = GWT.create(DatesJsonWriter.class);

    public interface DatesXmlReader extends XmlReader<Dates> {}
    public static final DatesXmlReader XML_READER = GWT.create(DatesXmlReader.class);

    public interface DatesXmlWriter extends XmlWriter<Dates> {}
    public static final DatesXmlWriter XML_WRITER = GWT.create(DatesXmlWriter.class);
    
    
    Date nullDate;
    Date iso8601Date;
    Date formatedDate;
    java.sql.Date sqlDate;
    java.sql.Time sqlTime;
    java.sql.Timestamp sqlTimestamp;
}
