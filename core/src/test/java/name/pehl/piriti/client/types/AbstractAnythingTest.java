package name.pehl.piriti.client.types;

import name.pehl.piriti.client.AbstractPiritiTest;

/**
 * Abstract base class for testing object mappings.
 * 
 * @author $Author: harald.pehl $
 * @version $Date: 2011-02-28 22:50:26 +0100 (Mo, 28 Feb 2011) $ $Revision: 295
 *          $
 */
public abstract class AbstractAnythingTest extends AbstractPiritiTest
{
    static final String ONE_THING = "one thing";
    static final String ANOTHER_THING = "another thing";


    protected void assertAnything(Anything anything)
    {
        assertNotNull(anything);
        assertEquals(anything.thing, ONE_THING);
        assertNotNull(anything.things);
        assertEquals(2, anything.things.size());
        assertEquals(anything.things.get(0), ONE_THING);
        assertEquals(anything.things.get(1), ANOTHER_THING);
    }
}
