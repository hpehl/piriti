package name.pehl.piriti.client.gwttest.employee;

import java.util.List;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class Employee
{
    public interface EmployeeJsonReader extends JsonReader<Employee>
    {
    }

    public static final EmployeeJsonReader JSON = GWT.create(EmployeeJsonReader.class);

    public interface EmployeeXmlReader extends XmlReader<Employee>
    {
    }

    public static final EmployeeXmlReader XML = GWT.create(EmployeeXmlReader.class);

    @JsonField
    @XmlField
    String name;

    @JsonField
    @XmlField
    Employee boos;

    @JsonField
    @XmlField("team/employee")
    List<Employee> team;


    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        Employee other = (Employee) obj;
        if (name == null)
        {
            if (other.name != null)
            {
                return false;
            }
        }
        else if (!name.equals(other.name))
        {
            return false;
        }
        return true;
    }


    @Override
    public String toString()
    {
        return "Employee[" + name + "]";
    }
}
