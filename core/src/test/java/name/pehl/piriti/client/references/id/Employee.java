package name.pehl.piriti.client.references.id;

import java.util.List;

import name.pehl.piriti.xml.client.Xml;
import name.pehl.piriti.xml.client.XmlId;
import name.pehl.piriti.xml.client.XmlIdRef;
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

    @XmlId
    String id;

    @Xml
    String name;

    @XmlIdRef("boss/@ref")
    Employee boss;

    @XmlIdRef("team/member/@ref")
    List<Employee> team;

    @Xml
    @XmlIdRef("department/@ref")
    Department department;


    // --------------------------------------------------------- public methods

    @Override
    public String toString()
    {
        return "Employee [" + id + "]";
    }
}
