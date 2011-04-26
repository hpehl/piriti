package name.pehl.piriti.client.references.id;


/**
 * @author $Author$
 * @version $Revision$
 */
public class XmlCompanyTest extends AbstractCompanyTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String xml = CompanyResources.INSTANCE.companyXml().getText();
        Company company = Company.XML.read(xml);
        assertCompany(company);
    }

    // ------------------------------------------------------------ write tests

    // NYI
}
