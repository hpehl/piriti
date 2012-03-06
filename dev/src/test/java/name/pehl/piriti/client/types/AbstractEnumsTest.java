package name.pehl.piriti.client.types;

import static name.pehl.piriti.client.types.Enums.Color.GREEN;
import static name.pehl.piriti.client.types.Enums.Color.RED;

import java.util.EnumSet;

import name.pehl.piriti.client.AbstractPiritiTest;
import name.pehl.piriti.client.types.Enums.Color;

/**
 * Abstract base class for testing boolean mappings.
 * 
 * @author $Author: harald.pehl $
 * @version $Date: 2011-02-28 22:50:26 +0100 (Mo, 28 Feb 2011) $ $Revision: 295
 *          $
 */
public abstract class AbstractEnumsTest extends AbstractPiritiTest
{
    protected void assertEnums(Enums enums)
    {
        assertNull(enums.colorless);
        assertEquals(GREEN, enums.favorite);
        assertEquals(EnumSet.allOf(Color.class), enums.white);
        assertEquals(3, enums.threeTimesRed.size());
        assertEquals(RED, enums.threeTimesRed.get(0));
        assertEquals(RED, enums.threeTimesRed.get(1));
        assertEquals(RED, enums.threeTimesRed.get(2));
    }
}
