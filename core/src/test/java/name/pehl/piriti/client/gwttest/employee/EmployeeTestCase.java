package name.pehl.piriti.client.gwttest.employee;

import java.util.List;

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
//        new Department();
//        new Employee();
    }


    protected void assertEmployees(List<Employee> employees)
    {
        assertNotNull(employees);
        assertEquals(5, employees.size());

        Employee boss = employees.get(0);
        assertNotNull(boss);
        assertEquals(EmployeeFactory.BOSS_ID, boss.id);
        assertEquals(EmployeeFactory.BOSS_NAME, boss.name);
        assertNull(boss.boss);
        assertNotNull(boss.team);
        assertEquals(2, boss.team.size());
        assertNotNull(boss.department);
        assertEquals(EmployeeFactory.BOARD_DEPARTMENT_ID, boss.department.id);
        assertEquals(EmployeeFactory.BOARD_DEPARTMENT_NAME, boss.department.name);
        assertNotNull(boss.department.employees);
        assertEquals(1, boss.department.employees.length);
        assertSame(boss, boss.department.employees[0]);

        Employee seller = boss.team.get(0);
        assertEquals(EmployeeFactory.SELLER_ID, seller.id);
        assertEquals(EmployeeFactory.SELLER_NAME, seller.name);
        assertSame(boss, seller.boss);
        assertNull(seller.team);
        assertNotNull(seller.department);
        assertEquals(EmployeeFactory.SALES_DEPARTMENT_ID, seller.department.id);
        assertEquals(EmployeeFactory.SALES_DEPARTMENT_NAME, seller.department.name);
        assertNotNull(seller.department.employees);
        assertEquals(1, seller.department.employees.length);
        assertSame(seller, seller.department.employees[0]);

        Employee engineer = boss.team.get(1);
        assertEquals(EmployeeFactory.ENGINEER_ID, engineer.id);
        assertEquals(EmployeeFactory.ENGINEER_NAME, engineer.name);
        assertSame(boss, engineer.boss);
        assertNotNull(engineer.team);
        assertEquals(2, engineer.team.size());

        Employee coder = engineer.team.get(0);
        assertEquals(EmployeeFactory.CODER_ID, coder.id);
        assertEquals(EmployeeFactory.CODER_NAME, coder.name);
        assertSame(engineer, coder.boss);
        assertNull(coder.team);

        Employee tester = engineer.team.get(1);
        assertEquals(EmployeeFactory.TESTER_ID, tester.id);
        assertEquals(EmployeeFactory.TESTER_NAME, tester.name);
        assertSame(engineer, tester.boss);
        assertNull(tester.team);

        Department itDepartment = engineer.department;
        assertNotNull(itDepartment);
        assertEquals(EmployeeFactory.IT_DEPARTMENT_ID, itDepartment.id);
        assertEquals(EmployeeFactory.IT_DEPARTMENT_NAME, itDepartment.name);
        assertNotNull(itDepartment.employees);
        assertEquals(3, itDepartment.employees.length);
        assertSame(engineer, itDepartment.employees[0]);
        assertSame(coder, itDepartment.employees[1]);
        assertSame(tester, itDepartment.employees[2]);

        assertSame(itDepartment, coder.department);
        assertSame(itDepartment, tester.department);
    }
}
