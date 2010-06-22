package name.pehl.piriti.client.gwttest.book;

import static name.pehl.piriti.client.gwttest.book.BookResources.*;

import java.util.List;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * @author $Author$
 * @version $Date$ $Revision: 295
 *          $
 */
public abstract class AbstractBookReaderTest extends GWTTestCase
{
    @Override
    public String getModuleName()
    {
        return "name.pehl.piriti.PiritiTest";
    }


    @Override
    protected void gwtSetUp() throws Exception
    {
        System.out.println("Running " + getClass().getName());
    }


    protected void assertBooks(List<Book> books, boolean withAuthor, boolean withRelated)
    {
        assertNotNull(books);
        assertEquals(BOOKS_COUNT, books.size());
        for (Book book : books)
        {
            assertBook(book, withAuthor, withRelated);
        }
    }


    protected void assertBook(Book book, boolean withAuthor, boolean withRelated)
    {
        assertNotNull(book);
        assertEquals(ISBN, book.isbn);
        assertEquals(PAGES, book.pages);
        assertEquals(TITLE, book.title);
        if (withAuthor)
        {
            assertEquals(AUTHOR_FIRSTNAME, book.author.firstname);
            assertEquals(AUTHOR_SURNAME, book.author.surname);
            assertBook(book.author.bestseller, false, false);
        }
        assertEquals(REVIEWS.length, book.reviews.size());
        for (int index = 0; index < REVIEWS.length; index++)
        {
            assertEquals(REVIEWS[index], book.reviews.get(index));
        }
        if (withRelated)
        {
            assertEquals(BOOKS_COUNT, book.related.size());
            for (Book related : book.related)
            {
                assertBook(related, true, false);
            }
        }
    }
}
