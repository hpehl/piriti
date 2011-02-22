package name.pehl.piriti.client.gwttest.references;

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
// @formatter:off
public class Book
{
    // --------------------------------------------------- json reader / writer

    public interface BookJsonWriter extends JsonWriter<Book> {}
    public static final BookJsonWriter JSON_WRITER = GWT.create(BookJsonWriter.class);

    public interface BookJsonReader extends JsonReader<Book> {}
    public static final BookJsonReader JSON_READER = GWT.create(BookJsonReader.class);

    // ---------------------------------------------------- xml reader / writer

    public interface BookXmlReader extends XmlReader<Book> {}
    public static final BookXmlReader XML_READER = GWT.create(BookXmlReader.class);

    public interface BookXmlWriter extends XmlWriter<Book> {}
    public static final BookXmlWriter XML_WRITER = GWT.create(BookXmlWriter.class);

    // ------------------------------------------------------------------- data

    @Json @Xml String isbn;
    @Json @Xml int pages;
    @Json @Xml String title;
    @Json @Xml Author author;
    @Json @Xml("reviews/review") List<String> reviews;
    @Json @Xml("related/book") List<Book> related;
    
    @Json("@.related[2].extraInfo") 
    @Xml("//related/book[3]/extraInfo/text()") 
    String extraInfoOfLastRelatedBook;
}
