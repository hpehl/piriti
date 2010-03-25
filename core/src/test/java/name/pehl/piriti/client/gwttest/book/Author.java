package name.pehl.piriti.client.gwttest.book;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-02-15 16:03:08 +0100 (Mo, 15 Feb 2010) $ $Revision: 131
 *          $
 */
public class Author
{
    public interface AuthorXmlReader extends XmlReader<Author>
    {
    }

    public static final AuthorXmlReader XML = GWT.create(AuthorXmlReader.class);

    public interface AuthorJsonReader extends JsonReader<Author>
    {
    }

    public static final AuthorJsonReader JSON = GWT.create(AuthorJsonReader.class);

    @XmlField
    @JsonField
    public String firstname;

    @XmlField
    @JsonField
    public String surname;
}
