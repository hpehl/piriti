package name.pehl.piriti.client;

import java.util.Date;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * @author $Author$
 * @version $Date$ $Revision: 629
 *          $
 */
public abstract class AbstractPiritiTest extends GWTTestCase
{
    @Override
    public String getModuleName()
    {
        return "name.pehl.piriti.PiritiTest";
    }


    @Override
    protected void gwtSetUp() throws Exception
    {
        System.out.println(getClass().getName() + "." + getName() + "()");
    }


    /**
     * Dates are not compared with <code>equals()</code>. To prevent daylight
     * saving probblems only the the day, month and year is compared.
     * 
     * @param expected
     * @param actual
     */
    @SuppressWarnings("deprecation")
    protected void assertDate(Date expected, Date actual)
    {
        if (expected != null && actual != null)
        {
            assertEquals(expected.getYear(), actual.getYear());
            assertEquals(expected.getMonth(), actual.getMonth());
            assertEquals(expected.getDate(), actual.getDate());
        }
    }
}
