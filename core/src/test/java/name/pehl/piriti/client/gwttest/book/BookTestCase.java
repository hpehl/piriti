package name.pehl.piriti.client.gwttest.book;

import java.util.List;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * @author $Author$
 * @version $Date$ $Revision: 295
 *          $
 */
public abstract class BookTestCase extends GWTTestCase
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

        // Register readers
//        new Book();
//        new Author();
    }


    protected void assertBooks(List<Book> books, boolean withAuthor, boolean withRelated)
    {
        assertNotNull(books);
        assertEquals(BookFactory.BOOKS_COUNT, books.size());
        for (Book book : books)
        {
            assertBook(book, withAuthor, withRelated);
        }
    }


    protected void assertBook(Book book, boolean withAuthor, boolean withRelated)
    {
        assertNotNull(book);
        assertEquals(BookFactory.ISBN, book.isbn);
        assertEquals(BookFactory.PAGES, book.pages);
        assertEquals(BookFactory.TITLE, book.title);
        if (withAuthor)
        {
            assertEquals(BookFactory.AUTHOR_FIRSTNAME, book.author.firstname);
            assertEquals(BookFactory.AUTHOR_SURNAME, book.author.surname);
            assertBook(book.author.bestseller, false, false);
        }
        assertEquals(BookFactory.REVIEWS.length, book.reviews.size());
        for (int index = 0; index < BookFactory.REVIEWS.length; index++)
        {
            assertEquals(BookFactory.REVIEWS[index], book.reviews.get(index));
        }
        if (withRelated)
        {
            assertEquals(BookFactory.BOOKS_COUNT, book.related.size());
            for (Book related : book.related)
            {
                assertBook(related, true, false);
            }
        }
    }
}
