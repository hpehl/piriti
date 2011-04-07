package name.pehl.piriti.client.references.id;

import java.util.List;

import name.pehl.piriti.commons.client.Id;
import name.pehl.piriti.commons.client.IdRef;
import name.pehl.piriti.xml.client.Xml;
import name.pehl.piriti.xml.client.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 408
 *          $
 */
public class Employee
{
    // ---------------------------------------------------- xml reader / writer

    public interface EmployeeXmlReader extends XmlReader<Employee>
    {
    }

    public static final EmployeeXmlReader XML = GWT.create(EmployeeXmlReader.class);

    // ------------------------------------------------------------------- data

    @Id
    String id;

    @Xml
    String name;

    @IdRef("boss/@ref")
    Employee boss;

    @IdRef("team/member/@ref")
    List<Employee> team;

    @Xml
    @IdRef("department/@ref")
    Department department;


    // --------------------------------------------------------- public methods

    @Override
    public String toString()
    {
        return "Employee [" + id + "]";
    }
}
