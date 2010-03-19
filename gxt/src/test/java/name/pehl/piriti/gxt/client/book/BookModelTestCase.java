package name.pehl.piriti.gxt.client.book;

import name.pehl.piriti.client.book.BookTestCase;

/**
 * @author $Author$
 * @version $Date$ $Revision: 161
 *          $
 */
public abstract class BookModelTestCase extends BookTestCase
{
    @Override
    public String getModuleName()
    {
        return "name.pehl.piriti.gxt.PiritiGxtTest";
    }


    @Override
    protected void gwtSetUp() throws Exception
    {
        System.out.println("Running " + getClass().getName());

        // Register readers
        new BookModel();
        new AuthorModel();
    }
}
