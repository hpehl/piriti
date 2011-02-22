package name.pehl.piriti.gxt.client.gwttest.references;

import name.pehl.piriti.gxt.client.json.Json;
import name.pehl.piriti.gxt.client.json.JsonMappings;
import name.pehl.piriti.gxt.client.json.JsonModelReader;
import name.pehl.piriti.gxt.client.xml.Xml;
import name.pehl.piriti.gxt.client.xml.XmlMappings;
import name.pehl.piriti.gxt.client.xml.XmlModelReader;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 131
 *          $
 */
//@formatter:off
@JsonMappings({
    @Json(property = "firstname", type = String.class), 
    @Json(property = "surname", type = String.class),
    @Json(property = "bestseller", type = Book.class)})
@XmlMappings({
    @Xml(property = "firstname", type = String.class), 
    @Xml(property = "surname", type = String.class),
    @Xml(property = "bestseller", path = "bestseller/book", type = Book.class)})
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
