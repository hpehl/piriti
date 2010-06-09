package name.pehl.piriti.sample.client.model;

import name.pehl.piriti.gxt.client.json.JsonField;
import name.pehl.piriti.gxt.client.json.JsonModel;
import name.pehl.piriti.gxt.client.json.JsonModelReader;
import name.pehl.piriti.gxt.client.xml.XmlField;
import name.pehl.piriti.gxt.client.xml.XmlModel;
import name.pehl.piriti.gxt.client.xml.XmlModelReader;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.core.client.GWT;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-05-18 23:30:44 +0200 (Di, 18 Mai 2010) $ $Revision: 327
 *          $
 */
@XmlModel( {@XmlField(property = "firstname", type = String.class),
        @XmlField(property = "surname", type = String.class)})
@JsonModel( {@JsonField(property = "firstname", type = String.class),
        @JsonField(property = "surname", type = String.class)})
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
