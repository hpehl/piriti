package name.pehl.piriti.client.gwttest.book;

import java.util.List;

import name.pehl.piriti.client.json.Json;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.json.JsonWriter;
import name.pehl.piriti.client.xml.Xml;
import name.pehl.piriti.client.xml.XmlReader;
import name.pehl.piriti.client.xml.XmlWriter;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-02-15 16:03:08 +0100 (Mo, 15 Feb 2010) $ $Revision: 131
 *          $
 */
public class Book
{
    // --------------------------------------------------- json reader / writer

    // @formatter:off
    public interface BookJsonWriter extends JsonWriter<Book> {}
    public static final BookJsonWriter JSON_WRITER = GWT.create(BookJsonWriter.class);

    public interface BookJsonReader extends JsonReader<Book> {}
    public static final BookJsonReader JSON_READER = GWT.create(BookJsonReader.class);
    // @formatter:on

    // ---------------------------------------------------- xml reader / writer

    // @formatter:off
    public interface BookXmlReader extends XmlReader<Book> {}
    public static final BookXmlReader XML_READER = GWT.create(BookXmlReader.class);

    public interface BookXmlWriter extends XmlWriter<Book> {}
    public static final BookXmlWriter XML_WRITER = GWT.create(BookXmlWriter.class);
    // @formatter:on

    // ------------------------------------------------------------------- data

    @Json
    @Xml
    String isbn;

    @Json
    @Xml
    int pages;

    @Json
    @Xml
    String title;

    @Json
    @Xml
    Author author;

    @Json
    @Xml("reviews/review")
    List<String> reviews;

    @Json
    @Xml("related/book")
    List<Book> related;

    @Json("@.related.length")
    @Xml("count(//related/book)")
    int relatedSize;
}
