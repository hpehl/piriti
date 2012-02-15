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

        assertNotNull(library.mediumsArray);
        assertEquals(3, library.mediumsArray.length);
        assertTrue(library.mediumsArray[0] instanceof Book);
        assertTrue(library.mediumsArray[1] instanceof Cd);
        assertTrue(library.mediumsArray[2] instanceof Dvd);

        assertNotNull(library.mediumsList);
        assertEquals(3, library.mediumsList.size());
        assertTrue(library.mediumsList.get(0) instanceof Book);
        assertTrue(library.mediumsList.get(1) instanceof Cd);
        assertTrue(library.mediumsList.get(2) instanceof Dvd);
    }
}
