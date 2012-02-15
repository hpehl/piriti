package name.pehl.piriti.client.types;

import name.pehl.piriti.client.AbstractPiritiTest;

/**
 * Abstract base class for testing boolean mappings.
 * 
 * @author $Author: harald.pehl $
 * @version $Date: 2011-02-28 22:50:26 +0100 (Mo, 28 Feb 2011) $ $Revision: 295
 *          $
 */
public abstract class AbstractNullsTest extends AbstractPiritiTest
{
    protected void assertNulls(Nulls nulls)
    {
        assertEquals("hello", nulls.stringA);
        assertNull(nulls.stringB);
        assertEquals(23, nulls.integerA.intValue());
        assertNull(nulls.integerB);
        assertEquals(4.2, nulls.doubleA.doubleValue(), 0.01);
        assertNull(nulls.doubleB);
    }
}
