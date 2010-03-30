package name.pehl.piriti.client.gwttest.employee.xml;

import static name.pehl.piriti.client.gwttest.XmlFactoryHelper.*;
import name.pehl.piriti.client.gwttest.employee.EmployeeFactory;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.XMLParser;

/**
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public final class XmlEmployeeFactory implements EmployeeFactory
{
    private XmlEmployeeFactory()
    {
    }


    public static Document createEmployees()
    {
        Document document = XMLParser.createDocument();
        Element employees = document.createElement("employees");

        Element board = createDepartment(document, BOARD_DEPARTMENT_ID, BOARD_DEPARTMENT_NAME, "boss");
        Element boss = createEmployee(document, BOSS_ID, BOSS_NAME, null, new String[] {"seller", "engineer"}, board,
                null);
        employees.appendChild(boss);

        Element sales = createDepartment(document, SALES_DEPARTMENT_ID, SALES_DEPARTMENT_NAME, "seller");
        Element seller = createEmployee(document, SELLER_ID, SELLER_NAME, "boss", null, sales, null);
        employees.appendChild(seller);

        Element it = createDepartment(document, IT_DEPARTMENT_ID, IT_DEPARTMENT_NAME, "engineer coder tester");
        Element engineer = createEmployee(document, ENGINEER_ID, ENGINEER_NAME, "boss",
                new String[] {"coder", "tester"}, it, null);
        employees.appendChild(engineer);

        Element coder = createEmployee(document, CODER_ID, CODER_NAME, "engineer", null, null, "it");
        employees.appendChild(coder);

        Element tester = createEmployee(document, TESTER_ID, TESTER_NAME, "engineer", null, null, "it");
        employees.appendChild(tester);

        document.appendChild(employees);
        return document;
    }


    private static Element createEmployee(Document document, String id, String name, String bossRef, String[] teamRefs,
            Element departmentElement, String departmentRef)
    {
        Element employee = document.createElement("employee");
        employee.setAttribute("id", id);
        createElementAndAppend(document, employee, "name", name);
        if (bossRef != null)
        {
            Element boss = document.createElement("boss");
            boss.setAttribute("ref", bossRef);
            employee.appendChild(boss);
        }
        if (teamRefs != null)
        {
            Element team = document.createElement("team");
            for (String teamRef : teamRefs)
            {
                Element member = document.createElement("member");
                member.setAttribute("ref", teamRef);
                team.appendChild(member);
            }
            employee.appendChild(team);
        }
        if (departmentElement != null)
        {
            employee.appendChild(departmentElement);
        }
        else if (departmentRef != null)
        {
            Element departmentRefElement = document.createElement("department");
            departmentRefElement.setAttribute("ref", departmentRef);
            employee.appendChild(departmentRefElement);
        }
        return employee;
    }


    private static Element createDepartment(Document document, String id, String name, String employees)
    {
        Element department = document.createElement("department");
        department.setAttribute("id", id);
        createElementAndAppend(document, department, "name", name);
        if (employees != null)
        {
            Element employeesElement = document.createElement("employees");
            employeesElement.setAttribute("members", employees);
            department.appendChild(employeesElement);
        }
        return department;
    }
}
