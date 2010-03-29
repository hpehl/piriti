package name.pehl.piriti.gxt.client.gwttest.book;

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
@JsonModel( {@JsonField(property = "firstname", type = String.class),
        @JsonField(property = "surname", type = String.class), @JsonField(property = "bestseller", type = Book.class)})
@XmlModel( {@XmlField(property = "firstname", type = String.class),
        @XmlField(property = "surname", type = String.class),
        @XmlField(path = "bestseller/book", property = "bestseller", type = Book.class)})
public class Author extends BaseModel
{
    public interface AuthorJsonReader extends JsonModelReader<Author>
    {
    }

    public static final AuthorJsonReader JSON = GWT.create(AuthorJsonReader.class);

    public interface AuthorXmlReader extends XmlModelReader<Author>
    {
    }

    public static final AuthorXmlReader XML = GWT.create(AuthorXmlReader.class);
}
