package name.pehl.piriti.client.gwttest.backandforth;

import java.util.Date;

import name.pehl.piriti.client.json.Json;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.json.JsonWriter;
import name.pehl.piriti.client.xml.Xml;
import name.pehl.piriti.client.xml.XmlReader;
import name.pehl.piriti.client.xml.XmlWriter;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
//@formatter:off
public class BackAndForth
{
    // --------------------------------------------------- json reader / writer

    public interface BackAndForthJsonWriter extends JsonWriter<BackAndForth> {}
    public static final BackAndForthJsonWriter JSON_WRITER = GWT.create(BackAndForthJsonWriter.class);

    public interface BackAndForthJsonReader extends JsonReader<BackAndForth> {}
    public static final BackAndForthJsonReader JSON_READER = GWT.create(BackAndForthJsonReader.class);

    // ---------------------------------------------------- xml reader / writer

    public interface BackAndForthXmlReader extends XmlReader<BackAndForth> {}
    public static final BackAndForthXmlReader XML_READER = GWT.create(BackAndForthXmlReader.class);

    public interface BackAndForthXmlWriter extends XmlWriter<BackAndForth> {}
    public static final BackAndForthXmlWriter XML_WRITER = GWT.create(BackAndForthXmlWriter.class);

    // ------------------------------------------------------------------- data

    @Json(converter = DurationConverter.class)
    @Xml(converter = DurationConverter.class)
    int duration;
    
    @Json(converter = TomorrowConverter.class)
    @Xml(converter = TomorrowConverter.class)
    Date date;
    
    @Json(converter = MathsConverter.class)
    @Xml(converter = MathsConverter.class)
    Maths maths;
    
    @Json(converter = NameConverter.class, format = NameConverter.DE_EN)
    @Xml(converter = NameConverter.class, format = NameConverter.DE_EN)
    String name;
    
    @Json(converter = DefaultValueConverter.class) int noConverter = 456;
}
