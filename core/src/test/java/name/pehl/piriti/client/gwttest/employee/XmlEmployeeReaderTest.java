package name.pehl.piriti.client.gwttest.employee;

import java.util.List;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;

/**
 * @author $Author$
 * @version $Revision$
 */
public class XmlEmployeeReaderTest extends AbstractEmployeeReaderTest
{
    public void testRead()
    {
        String xml = EmployeeResources.INSTANCE.employeesXml().getText();
        Document document = XMLParser.parse(xml);
        List<Employee> employees = Employee.XML.readList(document);
        assertEmployees(employees);
    }
}
