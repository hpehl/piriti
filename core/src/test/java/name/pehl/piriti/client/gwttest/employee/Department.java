package name.pehl.piriti.client.gwttest.employee;

import java.util.List;

import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlId;
import name.pehl.piriti.client.xml.XmlIdRef;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class Department
{
    // public interface DepartmentJsonReader extends JsonReader<Department>
    // {
    // }
    //
    // public static final DepartmentJsonReader JSON =
    // GWT.create(DepartmentJsonReader.class);

    public interface DepartmentXmlReader extends XmlReader<Department>
    {
    }

    public static final DepartmentXmlReader XML = GWT.create(DepartmentXmlReader.class);

    @XmlId
    String id;

    @XmlField
    String name;

    @XmlIdRef("employees/@members")
    List<Employee> employees;
}
