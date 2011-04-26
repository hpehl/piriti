package name.pehl.piriti.client.polymorph;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 131 $
 */
public class JsonLibraryTest extends AbstractLibraryTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String json = LibraryResources.INSTANCE.libraryJson().getText();
        Library library = Library.READER.read(json);
        assertLibrary(library);
    }

    // ------------------------------------------------------------ write tests

    // NYI
}
