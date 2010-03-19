package name.pehl.piriti.gxt.client.book;

import java.util.List;

import name.pehl.piriti.gxt.client.json.JsonField;
import name.pehl.piriti.gxt.client.json.JsonModel;
import name.pehl.piriti.gxt.client.json.JsonModelReader;
import name.pehl.piriti.gxt.client.xml.XmlField;
import name.pehl.piriti.gxt.client.xml.XmlModel;
import name.pehl.piriti.gxt.client.xml.XmlModelReader;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 131
 *          $
 */
@JsonModel( {@JsonField(property = "isbn", type = String.class), @JsonField(property = "pages", type = Integer.class),
        @JsonField(property = "title", type = String.class), @JsonField(property = "author", type = Author.class),
        @JsonField(property = "reviews", type = List.class, typeVariable = String.class)})
@XmlModel( {@XmlField(property = "isbn", type = String.class), @XmlField(property = "pages", type = Integer.class),
        @XmlField(property = "title", type = String.class), @XmlField(property = "author", type = Author.class),
        @XmlField(path = "reviews/review", property = "reviews", type = List.class, typeVariable = String.class)})
public class Book extends BaseModel
{
    public interface BookXmlReader extends XmlModelReader<Book>
    {
    }

    public static final BookXmlReader XML = GWT.create(BookXmlReader.class);

    public interface BookJsonReader extends JsonModelReader<Book>
    {
    }

    public static final BookJsonReader JSON = GWT.create(BookJsonReader.class);
}
