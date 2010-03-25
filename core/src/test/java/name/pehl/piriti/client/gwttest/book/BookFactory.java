package name.pehl.piriti.client.gwttest.book;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 131 $
 */
public interface BookFactory
{
    String BOOKS = "books";
    int BOOKS_COUNT = 3;

    String ISBN = "978-0345417954";
    int PAGES = 432;
    String TITLE = "The Hotel New Hampshire";
    String AUTHOR_SURNAME = "Irving";
    String AUTHOR_FIRSTNAME = "John";
    String[] REVIEWS = new String[] {"A hectic gaudy saga with the verve of a Marx Brothers movie.",
            "Rejoice! John Irving has written another book according to your world. You must read this book.",
            "Spellbinding, intensely human, a high-wire act of dazzling virtuosity.",};
}
