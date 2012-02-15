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
public class Book extends Medium
{
    public interface JsonBookReader extends JsonReader<Book> {}
    public static final JsonBookReader JSON_READER = GWT.create(JsonBookReader.class);

    public interface JsonBookWriter extends JsonWriter<Book> {}
    public static final JsonBookWriter JSON_WRITER = GWT.create(JsonBookWriter.class);

    public interface XmlBookReader extends XmlReader<Book> {}
    public static final XmlBookReader XML_READER = GWT.create(XmlBookReader.class);

    public interface XmlBookWriter extends XmlWriter<Book> {}
    public static final XmlBookWriter XML_WRITER = GWT.create(XmlBookWriter.class);

    int pages;
}
