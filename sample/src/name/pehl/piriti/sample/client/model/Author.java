package name.pehl.piriti.sample.client.model;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 131
 *          $
 */
public class Author
{
    // ---------------------------------------------------------------- readers

    public interface AuthorJsonReader extends JsonReader<Author>
    {
    }

    AuthorJsonReader JSON = GWT.create(AuthorJsonReader.class);

    public interface AuthorXmlReader extends XmlReader<Author>
    {
    }

    AuthorXmlReader XML = GWT.create(AuthorXmlReader.class);

    // ---------------------------------------------------------------- members

    @XmlField
    @JsonField
    String firstname;

    @XmlField
    @JsonField
    String surname;


    // ----------------------------------------------------------- constructors

    public Author(String firstname, String surname)
    {
        super();
        this.firstname = firstname;
        this.surname = surname;
    }


    // --------------------------------------------------------- public methods

    @Override
    public String toString()
    {
        return new StringBuilder().append("Author [").append(firstname).append(", ").append(surname).append("]")
                .toString();
    }


    // ------------------------------------------------------------- properties

    public String getFirstname()
    {
        return firstname;
    }


    public String getSurname()
    {
        return surname;
    }
}
