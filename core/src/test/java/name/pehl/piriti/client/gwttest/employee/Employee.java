package name.pehl.piriti.client.gwttest.employee;

import java.util.List;

import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlId;
import name.pehl.piriti.client.xml.XmlIdRef;
import name.pehl.piriti.client.xml.XmlReader;

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

    @XmlId
    String id;

    @XmlField
    String name;

    @XmlIdRef("boss/@ref")
    Employee boss;

    @XmlIdRef("team/member/@ref")
    List<Employee> team;

    @XmlField
    @XmlIdRef("department/@ref")
    Department department;


    // --------------------------------------------------------- public methods

    @Override
    public String toString()
    {
        return "Employee [" + id + "]";
    }
}
