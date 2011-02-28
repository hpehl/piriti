package name.pehl.piriti.client.gwttest.escape;

import name.pehl.piriti.client.json.Json;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.json.JsonWriter;
import name.pehl.piriti.client.xml.XmlReader;
import name.pehl.piriti.client.xml.XmlWriter;

import com.google.gwt.core.client.GWT;

// @formatter:off
public class Refugee
{
    // --------------------------------------------------- json reader / writer

    public interface RefugeeJsonWriter extends JsonWriter<Refugee> {}
    public static final RefugeeJsonWriter JSON_WRITER = GWT.create(RefugeeJsonWriter.class);

    public interface RefugeeJsonReader extends JsonReader<Refugee> {}
    public static final RefugeeJsonReader JSON_READER = GWT.create(RefugeeJsonReader.class);

    // ---------------------------------------------------- xml reader / writer

    public interface RefugeeXmlReader extends XmlReader<Refugee> {}
    public static final RefugeeXmlReader XML_READER = GWT.create(RefugeeXmlReader.class);

    public interface RefugeeXmlWriter extends XmlWriter<Refugee> {}
    public static final RefugeeXmlWriter XML_WRITER = GWT.create(RefugeeXmlWriter.class);

    // ------------------------------------------------------------------- data

    @Json String data;
    @Json(converter = DataConverter.class) String converterData;
}
