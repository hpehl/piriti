package name.pehl.piriti.gxt.client.book;

import java.util.List;

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
@XmlModel( {@XmlField(property = "isbn", type = String.class), @XmlField(property = "pages", type = Integer.class),
        @XmlField(property = "title", type = String.class), @XmlField(property = "author", type = AuthorModel.class),
        @XmlField(path = "reviews/review", property = "reviews", type = List.class)})
public class BookModel extends BaseModel
{
    public interface BookModelXmlReader extends XmlModelReader<BookModel>
    {
    }

    public static final BookModelXmlReader XML = GWT.create(BookModelXmlReader.class);

    public interface BookModelJsonReader extends JsonModelReader<BookModel>
    {
    }

    public static final BookModelJsonReader JSON = GWT.create(BookModelJsonReader.class);
}
