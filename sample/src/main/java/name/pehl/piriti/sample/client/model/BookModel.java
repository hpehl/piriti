package name.pehl.piriti.sample.client.model;

import java.util.List;

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
 * @version $Date$ $Revision: 327
 *          $
 */
// @formatter:off
@XmlMappings({
    @Xml(property = "isbn", type = String.class), 
    @Xml(property = "pages", type = Integer.class),
    @Xml(property = "title", type = String.class), 
    @Xml(property = "author", type = AuthorModel.class),
    @Xml(path = "reviews/review", property = "reviews", type = List.class, typeVariable = String.class)})
@JsonMappings({
    @Json(property = "isbn", type = String.class),
    @Json(property = "pages", type = Integer.class),
    @Json(property = "title", type = String.class), 
    @Json(property = "author", type = AuthorModel.class),
    @Json(property = "reviews", type = List.class, typeVariable = String.class)})
public class BookModel extends BaseModel
{
    public interface BookXmlReader extends XmlModelReader<BookModel> {}
    public static final BookXmlReader XML = GWT.create(BookXmlReader.class);

    public interface BookJsonReader extends JsonModelReader<BookModel> {}
    public static final BookJsonReader JSON = GWT.create(BookJsonReader.class);
}
