package name.pehl.piriti.gxt.client.gwttest.book;

import java.util.List;

import name.pehl.piriti.client.gwttest.book.BookFactory;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * @author $Author$
 * @version $Date$ $Revision: 161
 *          $
 */
public abstract class BookTestCase extends GWTTestCase
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
        new Book();
        new Author();
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
        assertEquals(BookFactory.ISBN, book.get("isbn"));
        assertEquals(BookFactory.PAGES, book.get("pages"));
        assertEquals(BookFactory.TITLE, book.get("title"));
        if (withAuthor)
        {
            Author author = book.get("author");
            assertEquals(BookFactory.AUTHOR_FIRSTNAME, author.get("firstname"));
            assertEquals(BookFactory.AUTHOR_SURNAME, author.get("surname"));
            assertBook((Book) author.get("bestseller"), false, false);
        }
        List<String> reviews = book.get("reviews");
        assertEquals(BookFactory.REVIEWS.length, reviews.size());
        for (int index = 0; index < BookFactory.REVIEWS.length; index++)
        {
            assertEquals(BookFactory.REVIEWS[index], reviews.get(index));
        }
        if (withRelated)
        {
            List<Book> related = book.get("related");
            assertEquals(BookFactory.BOOKS_COUNT, related.size());
            for (Book relatedEntry : related)
            {
                assertBook(relatedEntry, true, false);
            }
        }
    }
}
