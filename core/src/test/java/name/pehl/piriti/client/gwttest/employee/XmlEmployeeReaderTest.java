package name.pehl.piriti.client.gwttest.employee;

import java.util.List;

import name.pehl.piriti.client.xml.Node;
import name.pehl.piriti.client.xml.XmlGinjector;
import name.pehl.piriti.client.xml.XmlParser;

/**
 * @author $Author$
 * @version $Revision$
 */
public class XmlEmployeeReaderTest extends AbstractEmployeeReaderTest
{
    public void testRead()
    {
        String xml = EmployeeResources.INSTANCE.employeesXml().getText();
        XmlParser xmlParser = XmlGinjector.INJECTOR.getXmlParser();
        Node node = xmlParser.parse(xml);
        List<Employee> employees = Employee.XML.readList(node);
        assertEmployees(employees);
    }
}
