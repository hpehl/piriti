package name.pehl.piriti.client.polymorph;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2011-02-22 22:55:05 +0100 (Di, 22. Feb 2011) $ $Revision:
 *          1454 $
 */
//@formatter:off
public class Dvd extends Medium
{
    public interface JsonDvdReader extends JsonReader<Dvd> {}
    public static final JsonDvdReader JSON_READER = GWT.create(JsonDvdReader.class);

    public interface JsonDvdWriter extends JsonWriter<Dvd> {}
    public static final JsonDvdWriter JSON_WRITER = GWT.create(JsonDvdWriter.class);

    public interface XmlDvdReader extends XmlReader<Dvd> {}
    public static final XmlDvdReader XML_READER = GWT.create(XmlDvdReader.class);

    public interface XmlDvdWriter extends XmlWriter<Dvd> {}
    public static final XmlDvdWriter XML_WRITER = GWT.create(XmlDvdWriter.class);

    int duration;
}
