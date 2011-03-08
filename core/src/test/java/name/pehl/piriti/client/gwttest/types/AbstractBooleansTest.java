package name.pehl.piriti.client.gwttest.types;

import name.pehl.piriti.client.gwttest.AbstractPiritiTest;

/**
 * Abstract base class for testing boolean mappings.
 * 
 * @author $Author: harald.pehl $
 * @version $Date: 2011-02-28 22:50:26 +0100 (Mo, 28 Feb 2011) $ $Revision: 295
 *          $
 */
public abstract class AbstractBooleansTest extends AbstractPiritiTest
{
    protected void assertBooleans(Booleans booleans)
    {
        assertTrue(booleans.isB1());
        assertFalse(booleans.b2);
    }
}
