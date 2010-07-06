package name.pehl.piriti.client.gwttest.employee;

import java.util.List;

import name.pehl.totoe.client.Document;
import name.pehl.totoe.client.XmlParser;

/**
 * @author $Author$
 * @version $Revision$
 */
public class XmlEmployeeReaderTest extends AbstractEmployeeReaderTest
{
    public void testRead()
    {
        String xml = EmployeeResources.INSTANCE.employeesXml().getText();
        Document document = new XmlParser().parse(xml);
        List<Employee> employees = Employee.XML.readList(document);
        assertEmployees(employees);
    }
}
