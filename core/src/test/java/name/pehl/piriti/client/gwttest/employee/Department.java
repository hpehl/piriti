package name.pehl.piriti.client.gwttest.employee;

import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlId;
import name.pehl.piriti.client.xml.XmlIdRef;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 421
 *          $
 */
public class Department
{
    public interface DepartmentXmlReader extends XmlReader<Department>
    {
    }

    public static final DepartmentXmlReader XML = GWT.create(DepartmentXmlReader.class);

    @XmlId
    String id;

    @XmlField
    String name;

    @XmlIdRef("employees/@members")
    Employee[] employees;


    @Override
    public String toString()
    {
        return "Department [" + id + "]";
    }
}
