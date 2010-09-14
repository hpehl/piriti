package name.pehl.piriti.gxt.client.gwttest;

import java.util.Date;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-07-15 15:31:16 +0200 (Do, 15 Jul 2010) $ $Revision: 629
 *          $
 */
public abstract class AbstractPiritiGxtTest extends GWTTestCase
{
    @Override
    public String getModuleName()
    {
        return "name.pehl.piriti.gxt.PiritiGxtTest";
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
