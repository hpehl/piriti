package name.pehl.piriti.client.gwttest.backandforth;

import name.pehl.piriti.client.gwttest.AbstractPiritiTest;

/**
 * @author $Author$
 * @version $Date$ $Revision: 295
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
