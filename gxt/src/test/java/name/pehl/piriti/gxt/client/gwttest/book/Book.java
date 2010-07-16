package name.pehl.piriti.gxt.client.gwttest.book;

import java.util.List;

import name.pehl.piriti.gxt.client.json.JsonField;
import name.pehl.piriti.gxt.client.json.JsonFields;
import name.pehl.piriti.gxt.client.json.JsonModelReader;
import name.pehl.piriti.gxt.client.xml.XmlField;
import name.pehl.piriti.gxt.client.xml.XmlFields;
import name.pehl.piriti.gxt.client.xml.XmlModelReader;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 131
 *          $
 */
@JsonFields( {@JsonField(name = "isbn", type = String.class), @JsonField(name = "pages", type = Integer.class),
        @JsonField(name = "title", type = String.class), @JsonField(name = "author", type = Author.class),
        @JsonField(name = "reviews", type = List.class, typeVariable = String.class),
        @JsonField(name = "related", type = List.class, typeVariable = Book.class)})
@XmlFields( {@XmlField(name = "isbn", type = String.class), @XmlField(name = "pages", type = Integer.class),
        @XmlField(name = "title", type = String.class), @XmlField(name = "author", type = Author.class),
        @XmlField(name = "reviews", path = "reviews/review", type = List.class, typeVariable = String.class),
        @XmlField(name = "related", path = "related/book", type = List.class, typeVariable = Book.class)})
public class Book extends BaseModel
{
    public interface BookJsonReader extends JsonModelReader<Book>
    {
    }

    public static final BookJsonReader JSON = GWT.create(BookJsonReader.class);

    public interface BookXmlReader extends XmlModelReader<Book>
    {
    }

    public static final BookXmlReader XML = GWT.create(BookXmlReader.class);
}
