package name.pehl.piriti.client.polymorph;

import name.pehl.piriti.client.AbstractPiritiTest;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2011-03-20 21:02:59 +0100 (So, 20 Mrz 2011) $ $Revision: 295
 *          $
 */
public abstract class AbstractLibraryTest extends AbstractPiritiTest
{
    protected void assertLibrary(Library library)
    {
        assertNotNull(library);
        // NYI
        // assertEquals(3, library.mediums.size());
        // assertTrue(library.mediums.get(0) instanceof Book);
        // assertTrue(library.mediums.get(1) instanceof Cd);
        // assertTrue(library.mediums.get(2) instanceof Dvd);
    }
}
