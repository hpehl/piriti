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


    protected void assertEmployees(Employee boss)
    {
        assertNotNull(boss);
        assertEquals(EmployeeFactory.BOSS_ID, boss.id);
        assertEquals(EmployeeFactory.BOSS_NAME, boss.name);
        assertNull(boss.boss);
        assertNotNull(boss.team);
        assertEquals(2, boss.team.size());

        Employee seller = boss.team.get(0);
        assertEquals(EmployeeFactory.SELLER_ID, seller.id);
        assertEquals(EmployeeFactory.SELLER_NAME, seller.name);
        assertEquals(boss, seller.boss);
        assertNull(seller.team);

        Employee it = boss.team.get(1);
        assertEquals(EmployeeFactory.IT_ID, it.id);
        assertEquals(EmployeeFactory.IT_NAME, it.name);
        assertEquals(boss, it.boss);
        assertNotNull(it.team);
        assertEquals(2, it.team.size());

        Employee coder = it.team.get(0);
        assertEquals(EmployeeFactory.CODER_ID, coder.id);
        assertEquals(EmployeeFactory.CODER_NAME, coder.name);
        assertEquals(it, coder.boss);
        assertNull(coder.team);

        Employee tester = it.team.get(1);
        assertEquals(EmployeeFactory.TESTER_ID, tester.id);
        assertEquals(EmployeeFactory.TESTER_NAME, tester.name);
        assertEquals(it, tester.boss);
        assertNull(tester.team);
    }
}
