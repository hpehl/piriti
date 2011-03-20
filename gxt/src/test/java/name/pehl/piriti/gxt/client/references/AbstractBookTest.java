package name.pehl.piriti.gxt.client.references;

import static name.pehl.piriti.client.references.BookResources.*;

import java.util.List;

import name.pehl.piriti.gxt.client.AbstractPiritiGxtTest;

/**
 * @author $Author$
 * @version $Date$ $Revision: 161
 *          $
 */
public abstract class AbstractBookTest extends AbstractPiritiGxtTest
{
    protected void assertBook(Book book, boolean withAuthor, boolean withRelated)
    {
        assertNotNull(book);
        assertEquals(ISBN, book.get("isbn"));
        assertEquals(PAGES, book.get("pages"));
        assertEquals(TITLE, book.get("title"));
        if (withAuthor)
        {
            Author author = book.get("author");
            assertEquals(AUTHOR_FIRSTNAME, author.get("firstname"));
            assertEquals(AUTHOR_SURNAME, author.get("surname"));
            assertBook((Book) author.get("bestseller"), false, false);
        }
        List<String> reviews = book.get("reviews");
        assertEquals(REVIEWS.length, reviews.size());
        for (int index = 0; index < REVIEWS.length; index++)
        {
            assertEquals(REVIEWS[index], reviews.get(index));
        }
        if (withRelated)
        {
            List<Book> related = book.get("related");
            assertEquals(RELATED_SIZE, related.size());
            for (Book relatedEntry : related)
            {
                assertBook(relatedEntry, true, false);
            }
        }
    }
}
