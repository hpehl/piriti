package name.pehl.piriti.client.references.id;

import name.pehl.piriti.xml.client.Xml;
import name.pehl.piriti.xml.client.XmlId;
import name.pehl.piriti.xml.client.XmlIdRef;
import name.pehl.piriti.xml.client.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 421
 *          $
 */
public class Department
{
    // ---------------------------------------------------- xml reader / writer

    public interface DepartmentXmlReader extends XmlReader<Department>
    {
    }

    public static final DepartmentXmlReader XML = GWT.create(DepartmentXmlReader.class);

    // ------------------------------------------------------------------- data

    @XmlId
    String id;

    @Xml
    String name;

    @XmlIdRef("employees/@members")
    Employee[] employees;


    // --------------------------------------------------------- public methods

    @Override
    public String toString()
    {
        return "Department [" + id + "]";
    }
}
