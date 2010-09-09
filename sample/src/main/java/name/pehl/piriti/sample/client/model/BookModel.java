package name.pehl.piriti.sample.client.model;

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
 * @version $Date$ $Revision: 327
 *          $
 */
@XmlFields( {@XmlField(name = "isbn", type = String.class), @XmlField(name = "pages", type = Integer.class),
        @XmlField(name = "title", type = String.class), @XmlField(name = "author", type = Author.class),
        @XmlField(path = "reviews/review", name = "reviews", type = List.class, typeVariable = String.class)})
@JsonFields( {@JsonField(name = "isbn", type = String.class), @JsonField(name = "pages", type = Integer.class),
        @JsonField(name = "title", type = String.class), @JsonField(name = "author", type = Author.class),
        @JsonField(name = "reviews", type = List.class, typeVariable = String.class)})
public class BookModel extends BaseModel
{
    public interface BookXmlReader extends XmlModelReader<BookModel>
    {
    }

    public static final BookXmlReader XML = GWT.create(BookXmlReader.class);

    public interface BookJsonReader extends JsonModelReader<BookModel>
    {
    }

    public static final BookJsonReader JSON = GWT.create(BookJsonReader.class);
}
