package name.pehl.piriti.sample.server.model;

/**
 * @author $Author$
 * @version $Date$ $Revision: 131
 *          $
 */
public class Author
{
    private String firstname;
    private String surname;


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
