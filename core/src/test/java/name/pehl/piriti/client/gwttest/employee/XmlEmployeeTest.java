package name.pehl.piriti.client.gwttest.employee;

import java.util.List;

/**
 * @author $Author$
 * @version $Revision$
 */
public class XmlEmployeeTest extends AbstractEmployeeTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String xml = EmployeeResources.INSTANCE.employeesXml().getText();
        List<Employee> employees = Employee.XML.readList(xml);
        assertEmployees(employees);
    }

    // ------------------------------------------------------------ write tests

    // NYI
}
