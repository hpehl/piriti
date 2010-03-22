package name.pehl.piriti.sample.shared.model;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.client.xml.XmlField;

/**
 * @author $Author$
 * @version $Date$ $Revision: 131
 *          $
 */
public class Author
{
    @XmlField
    @JsonField
    String firstname;

    @XmlField
    @JsonField
    String surname;


    public Author(String firstname, String surname)
    {
        super();
        this.firstname = firstname;
        this.surname = surname;
    }


    public String getFirstname()
    {
        return firstname;
    }


    public String getSurname()
    {
        return surname;
    }


    @Override
    public String toString()
    {
        return new StringBuilder().append("Author [").append(firstname).append(", ").append(surname).append("]")
                .toString();
    }
}
