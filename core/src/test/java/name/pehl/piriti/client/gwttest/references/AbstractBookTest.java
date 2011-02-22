package name.pehl.piriti.client.gwttest.references;

import static name.pehl.piriti.client.gwttest.references.BookResources.*;
import name.pehl.piriti.client.gwttest.AbstractPiritiTest;

/**
 * @author $Author$
 * @version $Date$ $Revision: 295
 *          $
 */
public abstract class AbstractBookTest extends AbstractPiritiTest
{
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
            assertEquals(EXTRA_INFO, book.extraInfoOfLastRelatedBook);
            assertEquals(RELATED_SIZE, book.related.size());
            for (Book related : book.related)
            {
                assertBook(related, true, false);
            }
        }
    }
}
