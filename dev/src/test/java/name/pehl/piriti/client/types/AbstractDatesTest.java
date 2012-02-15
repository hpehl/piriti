package name.pehl.piriti.client.types;

import java.util.Date;

import name.pehl.piriti.client.AbstractPiritiTest;

/**
 * Abstract base class for testing boolean mappings.
 * 
 * @author $Author: harald.pehl $
 * @version $Date: 2011-02-28 22:50:26 +0100 (Mo, 28 Feb 2011) $ $Revision: 295
 *          $
 */
public abstract class AbstractDatesTest extends AbstractPiritiTest
{
    @SuppressWarnings("deprecation")
    static final Date BIRTHDAY = new Date(73, 8, 2);


    @SuppressWarnings("deprecation")
    protected void assertDates(Dates dates)
    {
        assertNull(dates.nullDate);
        assertDate(BIRTHDAY, dates.iso8601Date);
        assertDate(BIRTHDAY, dates.formatedDate);
        assertDate(BIRTHDAY, dates.sqlDate);
        assertDate(BIRTHDAY, dates.sqlTimestamp);

        assertEquals(11, dates.sqlTime.getHours());
        assertEquals(22, dates.sqlTime.getMinutes());
        assertEquals(33, dates.sqlTime.getSeconds());

        assertEquals(11, dates.sqlTimestamp.getHours());
        assertEquals(22, dates.sqlTimestamp.getMinutes());
        assertEquals(33, dates.sqlTimestamp.getSeconds());
    }
}
