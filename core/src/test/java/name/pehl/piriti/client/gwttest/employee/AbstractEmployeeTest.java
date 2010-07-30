package name.pehl.piriti.client.gwttest.employee;

import static name.pehl.piriti.client.gwttest.employee.EmployeeResources.*;

import java.util.List;

import name.pehl.piriti.client.gwttest.AbstractPiritiTest;

/**
 * @author $Author$
 * @version $Date$ $Revision: 295
 *          $
 */
public abstract class AbstractEmployeeTest extends AbstractPiritiTest
{
    protected void assertEmployees(List<Employee> employees)
    {
        assertNotNull(employees);
        assertEquals(5, employees.size());

        Employee boss = employees.get(0);
        assertNotNull(boss);
        assertEquals(BOSS_ID, boss.id);
        assertEquals(BOSS_NAME, boss.name);
        assertNull(boss.boss);
        assertNotNull(boss.team);
        assertEquals(2, boss.team.size());
        assertNotNull(boss.department);
        assertEquals(BOARD_DEPARTMENT_ID, boss.department.id);
        assertEquals(BOARD_DEPARTMENT_NAME, boss.department.name);
        assertNotNull(boss.department.employees);
        assertEquals(1, boss.department.employees.length);
        assertSame(boss, boss.department.employees[0]);

        Employee seller = boss.team.get(0);
        assertEquals(SELLER_ID, seller.id);
        assertEquals(SELLER_NAME, seller.name);
        assertSame(boss, seller.boss);
        assertNull(seller.team);
        assertNotNull(seller.department);
        assertEquals(SALES_DEPARTMENT_ID, seller.department.id);
        assertEquals(SALES_DEPARTMENT_NAME, seller.department.name);
        assertNotNull(seller.department.employees);
        assertEquals(1, seller.department.employees.length);
        assertSame(seller, seller.department.employees[0]);

        Employee engineer = boss.team.get(1);
        assertEquals(ENGINEER_ID, engineer.id);
        assertEquals(ENGINEER_NAME, engineer.name);
        assertSame(boss, engineer.boss);
        assertNotNull(engineer.team);
        assertEquals(2, engineer.team.size());

        Employee coder = engineer.team.get(0);
        assertEquals(CODER_ID, coder.id);
        assertEquals(CODER_NAME, coder.name);
        assertSame(engineer, coder.boss);
        assertNull(coder.team);

        Employee tester = engineer.team.get(1);
        assertEquals(TESTER_ID, tester.id);
        assertEquals(TESTER_NAME, tester.name);
        assertSame(engineer, tester.boss);
        assertNull(tester.team);

        Department itDepartment = engineer.department;
        assertNotNull(itDepartment);
        assertEquals(IT_DEPARTMENT_ID, itDepartment.id);
        assertEquals(IT_DEPARTMENT_NAME, itDepartment.name);
        assertNotNull(itDepartment.employees);
        assertEquals(3, itDepartment.employees.length);
        assertSame(engineer, itDepartment.employees[0]);
        assertSame(coder, itDepartment.employees[1]);
        assertSame(tester, itDepartment.employees[2]);

        assertSame(itDepartment, coder.department);
        assertSame(itDepartment, tester.department);
    }
}
