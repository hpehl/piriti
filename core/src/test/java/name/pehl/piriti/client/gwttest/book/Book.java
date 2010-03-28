package name.pehl.piriti.client.gwttest.book;

import java.util.List;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-02-15 16:03:08 +0100 (Mo, 15 Feb 2010) $ $Revision: 131
 *          $
 */
public class Book
{
    public interface BookXmlReader extends XmlReader<Book>
    {
    }

    public static final BookXmlReader XML = GWT.create(BookXmlReader.class);

    public interface BookJsonReader extends JsonReader<Book>
    {
    }

    public static final BookJsonReader JSON = GWT.create(BookJsonReader.class);

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
    
    @XmlField("related/book")
    @JsonField
    List<Book> related;
}
