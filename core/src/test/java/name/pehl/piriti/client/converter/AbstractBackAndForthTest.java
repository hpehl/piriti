package name.pehl.piriti.client.converter;

import static name.pehl.piriti.client.converter.BackAndForthResources.MY_BIRTHDAY_IN_MILLIS;

import java.util.Date;

import name.pehl.piriti.client.AbstractPiritiTest;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2011-02-22 22:55:05 +0100 (Di, 22. Feb 2011) $ $Revision: 295
 *          $
 */
public abstract class AbstractBackAndForthTest extends AbstractPiritiTest
{
    protected void assertBackAndForth(BackAndForth backAndForth)
    {
        assertNotNull(backAndForth);
        assertDateTime(1973, 9, 2, 11, 22, 33, backAndForth.date);
        assertEquals(backAndForth.millis.getTime(), MY_BIRTHDAY_IN_MILLIS);
        assertDate(2004, 9, 20, backAndForth.dateWithFormat);
        assertEquals(TomorrowConverter.TOMORROW_DATE, backAndForth.dateWithConverter);
        assertEquals(Maths.TWO, backAndForth.maths);
        assertEquals(Maths.TWO, backAndForth.mathsWithConverter);
        assertEquals(4, backAndForth.duration);
        assertEquals(NameConverter.ENGLISH, backAndForth.name);
    }


    @SuppressWarnings("deprecation")
    private void assertDate(int expectedYear, int expectedMonth, int expectedDay, Date actualDate)
    {
        assertNotNull(actualDate);
        assertEquals(expectedYear, actualDate.getYear() + 1900);
        assertEquals(expectedMonth, actualDate.getMonth() + 1);
        assertEquals(expectedDay, actualDate.getDate());
    }


    @SuppressWarnings("deprecation")
    private void assertDateTime(int expectedYear, int expectedMonth, int expectedDay, int expectedHours,
            int expectedMinutes, int expectedSeconds, Date actualDate)
    {
        assertDate(expectedYear, expectedMonth, expectedDay, actualDate);
        assertEquals(expectedHours, actualDate.getHours());
        assertEquals(expectedMinutes, actualDate.getMinutes());
        assertEquals(expectedSeconds, actualDate.getSeconds());
    }
}
