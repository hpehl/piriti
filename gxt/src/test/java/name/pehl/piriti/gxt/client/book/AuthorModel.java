package name.pehl.piriti.gxt.client.book;

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
        @JsonField(property = "surname", type = String.class)})
@XmlModel( {@XmlField(property = "firstname", type = String.class),
        @XmlField(property = "surname", type = String.class)})
public class AuthorModel extends BaseModel
{
    public interface AuthorModelXmlReader extends XmlModelReader<AuthorModel>
    {
    }

    public static final AuthorModelXmlReader XML = GWT.create(AuthorModelXmlReader.class);

    public interface AuthorModelJsonReader extends JsonModelReader<AuthorModel>
    {
    }

    public static final AuthorModelJsonReader JSON = GWT.create(AuthorModelJsonReader.class);
}
