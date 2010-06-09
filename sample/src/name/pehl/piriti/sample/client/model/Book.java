package name.pehl.piriti.sample.client.model;

import java.util.List;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-03-25 15:16:49 +0100 (Do, 25 Mrz 2010) $ $Revision: 131
 *          $
 */
public class Book
{
    // ---------------------------------------------------------------- readers

    public interface BookJsonReader extends JsonReader<Book>
    {
    }

    public static final BookJsonReader JSON = GWT.create(BookJsonReader.class);

    public interface BookXmlReader extends XmlReader<Book>
    {
    }

    public static final BookXmlReader XML = GWT.create(BookXmlReader.class);

    // ---------------------------------------------------------------- members

    @XmlField
    @JsonField
    String isbn;

    @XmlField
    @JsonField
    int pages;

    @XmlField
    @JsonField
    String title;

    @XmlField
    @JsonField
    Author author;

    @XmlField("reviews/review")
    @JsonField
    List<String> reviews;
}
