package name.pehl.piriti.client.gwttest.book;

import java.util.List;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.json.JsonWriter;
import name.pehl.piriti.client.xml.XmlField;
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

    public interface BookJsonWriter extends JsonWriter<Book>
    {
    }

    public static final BookJsonWriter JSON_WRITER = GWT.create(BookJsonWriter.class);

    public interface BookJsonReader extends JsonReader<Book>
    {
    }

    public static final BookJsonReader JSON_READER = GWT.create(BookJsonReader.class);

    public interface BookXmlReader extends XmlReader<Book>
    {
    }

    // ---------------------------------------------------- xml reader / writer

    public static final BookXmlReader XML_READER = GWT.create(BookXmlReader.class);

    public interface BookXmlWriter extends XmlWriter<Book>
    {
    }

    public static final BookXmlWriter XML_WRITER = GWT.create(BookXmlWriter.class);

    // ------------------------------------------------------------------- data

    @JsonField
    @XmlField
    String isbn;

    @JsonField
    @XmlField
    int pages;

    @JsonField
    @XmlField
    String title;

    @JsonField
    @XmlField
    Author author;

    @JsonField
    @XmlField("reviews/review")
    List<String> reviews;

    @JsonField
    @XmlField("related/book")
    List<Book> related;
}
