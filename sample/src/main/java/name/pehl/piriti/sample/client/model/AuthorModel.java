package name.pehl.piriti.sample.client.model;

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
    @Xml(property = "firstname", type = String.class),
    @Xml(property = "surname", type = String.class)})
@JsonMappings({
    @Json(property = "firstname", type = String.class),
    @Json(property = "surname", type = String.class)})
public class AuthorModel extends BaseModel
{
    public interface AuthorXmlReader extends XmlModelReader<AuthorModel> {}
    public static final AuthorXmlReader XML = GWT.create(AuthorXmlReader.class);

    public interface AuthorJsonReader extends JsonModelReader<AuthorModel> {}
    public static final AuthorJsonReader JSON = GWT.create(AuthorJsonReader.class);
}
