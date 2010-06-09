package name.pehl.piriti.sample.client.model;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-03-25 15:16:49 +0100 (Do, 25 Mrz 2010) $ $Revision: 131
 *          $
 */
public class Author
{
    // ---------------------------------------------------------------- readers

    public interface AuthorJsonReader extends JsonReader<Author>
    {
    }

    public static final AuthorJsonReader JSON = GWT.create(AuthorJsonReader.class);

    public interface AuthorXmlReader extends XmlReader<Author>
    {
    }

    public static final AuthorXmlReader XML = GWT.create(AuthorXmlReader.class);

    // ---------------------------------------------------------------- members

    @XmlField
    @JsonField
    String firstname;

    @XmlField
    @JsonField
    String surname;
}
