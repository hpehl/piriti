package name.pehl.piriti.client.references.id;

import java.util.List;

import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.xml.client.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2011-04-10 22:56:25 +0200 (So, 10. Apr 2011) $ $Revision: 421
 *          $
 */
public class Company
//@formatter:off
{
    // ---------------------------------------------------- xml reader / writer

    public interface CompanyXmlReader extends XmlReader<Company> { }
    public static final CompanyXmlReader XML = GWT.create(CompanyXmlReader.class);

    // ------------------------------------------------------------------- data

    @Path("employees/employee") List<Employee> employees;
    @Path("departments/department") List<Department> departments;
}
