package name.pehl.piriti.sample.client.model;

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
@XmlFields({@XmlField(name = "firstname", type = String.class),
        @XmlField(name = "surname", type = String.class)})
@JsonFields({@JsonField(name = "firstname", type = String.class),
        @JsonField(name = "surname", type = String.class)})
public class AuthorModel extends BaseModel
{
    public interface AuthorXmlReader extends XmlModelReader<AuthorModel>
    {
    }

    public static final AuthorXmlReader XML = GWT.create(AuthorXmlReader.class);

    public interface AuthorJsonReader extends JsonModelReader<AuthorModel>
    {
    }

    public static final AuthorJsonReader JSON = GWT.create(AuthorJsonReader.class);
}
