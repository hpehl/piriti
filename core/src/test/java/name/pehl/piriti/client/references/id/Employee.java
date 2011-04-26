package name.pehl.piriti.client.references.id;

import java.util.List;

import name.pehl.piriti.commons.client.Id;
import name.pehl.piriti.commons.client.IdRef;
import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.xml.client.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 408
 *          $
 */
//@formatter:off
public class Employee
{
    // ---------------------------------------------------- xml reader / writer

    public interface EmployeeXmlReader extends XmlReader<Employee> {}
    public static final EmployeeXmlReader XML = GWT.create(EmployeeXmlReader.class);

    // ------------------------------------------------------------------- data

    @Id @Path("@id") String id;
    String name;
    @IdRef @Path("boss/@ref") Employee boss;
    @IdRef @Path("team/member/@ref") List<Employee> team;
    @IdRef @Path("department/@ref") Department department;


    // --------------------------------------------------------- public methods

    @Override
    public String toString()
    {
        return "Employee [" + id + "]";
    }
}
