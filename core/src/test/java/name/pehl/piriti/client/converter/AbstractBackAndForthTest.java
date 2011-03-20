package name.pehl.piriti.client.converter;

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
        assertEquals(4, backAndForth.duration);
        assertEquals(TomorrowConverter.TOMORROW_DATE, backAndForth.date);
        assertEquals(Maths.TWO, backAndForth.maths);
        assertEquals(NameConverter.ENGLISH, backAndForth.name);
    }
}
