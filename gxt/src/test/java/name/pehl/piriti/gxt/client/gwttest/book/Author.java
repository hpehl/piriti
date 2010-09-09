package name.pehl.piriti.gxt.client.gwttest.book;

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
//@formatter:off
@JsonFields({
    @JsonField(name = "firstname", type = String.class), 
    @JsonField(name = "surname", type = String.class),
    @JsonField(name = "bestseller", type = Book.class)})
@XmlFields({
    @XmlField(name = "firstname", type = String.class), 
    @XmlField(name = "surname", type = String.class),
    @XmlField(name = "bestseller", path = "bestseller/book", type = Book.class)})
// @formatter:on
public class Author extends BaseModel
{
    // --------------------------------------------------- json reader / writer

    public interface AuthorJsonReader extends JsonModelReader<Author>
    {
    }

    public static final AuthorJsonReader JSON_READER = GWT.create(AuthorJsonReader.class);

    // ---------------------------------------------------- xml reader / writer

    public interface AuthorXmlReader extends XmlModelReader<Author>
    {
    }

    public static final AuthorXmlReader XML_READER = GWT.create(AuthorXmlReader.class);
}
