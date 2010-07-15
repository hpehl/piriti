package name.pehl.piriti.client.gwttest.book;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.client.json.JsonFields;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlFields;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class BookReader
{
    @JsonFields({@JsonField(name = "isbn"), @JsonField(name = "pages"), @JsonField(name = "title"),
            @JsonField(name = "author"), @JsonField(name = "reviews"), @JsonField(name = "related")})
    public interface BookJsonReader extends JsonReader<Book>
    {
    }

    public static final BookJsonReader JSON = GWT.create(BookJsonReader.class);

    @XmlFields({@XmlField(name = "isbn"), @XmlField(name = "pages"), @XmlField(name = "title"),
            @XmlField(name = "author"), @XmlField(name = "reviews", value = "reviews/review"),
            @XmlField(name = "related", value = "related/book")})
    public interface BookXmlReader extends XmlReader<Book>
    {
    }

    public static final BookXmlReader XML = GWT.create(BookXmlReader.class);
}
