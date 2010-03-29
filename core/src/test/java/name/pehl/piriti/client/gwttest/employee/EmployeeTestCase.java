package name.pehl.piriti.client.gwttest.employee;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * @author $Author$
 * @version $Date$ $Revision: 295
 *          $
 */
public abstract class EmployeeTestCase extends GWTTestCase
{
    @Override
    public String getModuleName()
    {
        return "name.pehl.piriti.PiritiTest";
    }


    @Override
    protected void gwtSetUp() throws Exception
    {
        System.out.println("Running " + getClass().getName());

        // Register readers
        new Employee();
    }


    protected void assertEmployee(Employee elPresidente)
    {
        assertNotNull(elPresidente);
        assertEquals(EmployeeFactory.EL_PRESIDENTE, elPresidente.name);
        assertNull(elPresidente.boos);
        assertNotNull(elPresidente.team);
        assertEquals(2, elPresidente.team.size());

        Employee seniorSalesman = elPresidente.team.get(0);
        assertEquals(EmployeeFactory.SENIOR_SALESMAN, seniorSalesman.name);
        assertEquals(elPresidente, seniorSalesman.boos);
        assertNotNull(seniorSalesman.team);
        assertEquals(1, seniorSalesman.team.size());

        Employee salesman = seniorSalesman.team.get(0);
        assertEquals(EmployeeFactory.SALESMAN, salesman.name);
        assertEquals(seniorSalesman, salesman.boos);
        assertNull(seniorSalesman.team);

        Employee cto = elPresidente.team.get(1);
        assertEquals(EmployeeFactory.CTO, cto.name);
        assertEquals(elPresidente, cto.boos);
        assertNotNull(cto.team);
        assertEquals(2, cto.team.size());

        Employee qa = cto.team.get(0);
        assertEquals(EmployeeFactory.QA, qa.name);
        assertEquals(cto, qa.boos);
        assertNull(qa.team);

        Employee developer = cto.team.get(0);
        assertEquals(EmployeeFactory.DEVELOPER, developer.name);
        assertEquals(cto, developer.boos);
        assertNull(developer.team);
    }
}
