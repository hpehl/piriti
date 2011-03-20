package name.pehl.piriti.gxt.client.references;

import java.util.List;

import name.pehl.piriti.gxt.json.client.Json;
import name.pehl.piriti.gxt.json.client.JsonMappings;
import name.pehl.piriti.gxt.json.client.JsonModelReader;
import name.pehl.piriti.gxt.xml.client.Xml;
import name.pehl.piriti.gxt.xml.client.XmlMappings;
import name.pehl.piriti.gxt.xml.client.XmlModelReader;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 131
 *          $
 */
//@formatter:off
@JsonMappings({
    @Json(property = "isbn", type = String.class), 
    @Json(property = "pages", type = Integer.class),
    @Json(property = "title", type = String.class), 
    @Json(property = "author", type = Author.class),
    @Json(property = "reviews", type = List.class, typeVariable = String.class),
    @Json(property = "related", type = List.class, typeVariable = Book.class)})
@XmlMappings({
    @Xml(property = "isbn", type = String.class), 
    @Xml(property = "pages", type = Integer.class),
    @Xml(property = "title", type = String.class), 
    @Xml(property = "author", type = Author.class),
    @Xml(property = "reviews", path = "reviews/review", type = List.class, typeVariable = String.class),
    @Xml(property = "related", path = "related/book", type = List.class, typeVariable = Book.class)})
// @formatter:on
public class Book extends BaseModel
{
    // --------------------------------------------------- json reader / writer

    public interface BookJsonReader extends JsonModelReader<Book>
    {
    }

    public static final BookJsonReader JSON_READER = GWT.create(BookJsonReader.class);

    // ---------------------------------------------------- xml reader / writer

    public interface BookXmlReader extends XmlModelReader<Book>
    {
    }

    public static final BookXmlReader XML_READER = GWT.create(BookXmlReader.class);
}
