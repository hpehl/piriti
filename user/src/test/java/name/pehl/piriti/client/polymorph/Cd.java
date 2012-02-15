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
public class Cd extends Medium
{
    public interface JsonCdReader extends JsonReader<Cd> {}
    public static final JsonCdReader JSON_READER = GWT.create(JsonCdReader.class);

    public interface JsonCdWriter extends JsonWriter<Cd> {}
    public static final JsonCdWriter JSON_WRITER = GWT.create(JsonCdWriter.class);

    public interface XmlCdReader extends XmlReader<Cd> {}
    public static final XmlCdReader XML_READER = GWT.create(XmlCdReader.class);

    public interface XmlCdWriter extends XmlWriter<Cd> {}
    public static final XmlCdWriter XML_WRITER = GWT.create(XmlCdWriter.class);

    int tracks;
}
