package name.pehl.piriti.client.gwttest.book.json;

import name.pehl.piriti.client.gwttest.book.BookFactory;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 131 $
 */
public final class JsonBookFactory implements BookFactory
{
    private JsonBookFactory()
    {
    }


    /**
     * Creates books with author and related books.
     * 
     * @return
     */
    public static String createBooks()
    {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append(BOOKS);
        json.append(": [");
        for (int i = 0; i < BOOKS_COUNT; i++)
        {
            appendValue(json, createBook(), false, (i < BOOKS_COUNT - 1));
        }
        json.append("]}");
        return json.toString();
    }


    /**
     * Creates a book with author and related books.
     * 
     * @return
     */
    public static String createBook()
    {
        return createBook(true, true);
    }


    /**
     * Create a book. Depending on the parameters {@code withAuthor} and
     * {@code withRelated} the author and related elements are also generated.
     * 
     * @param withAuthor
     * @param withRelated
     * @return
     */
    public static String createBook(boolean withAuthor, boolean withRelated)
    {
        StringBuilder json = new StringBuilder();

        json.append("{");
        appendKeyValue(json, "isbn", ISBN, true, true);
        appendKeyValue(json, "pages", String.valueOf(PAGES), false, true);
        appendKeyValue(json, "title", TITLE, true, true);
        if (withAuthor)
        {
            appendKeyValue(json, "author", createAuthor(), false, true);
        }
        json.append("reviews: [");
        for (int i = 0; i < REVIEWS.length; i++)
        {
            appendValue(json, REVIEWS[i], true, (i < REVIEWS.length - 1));
        }
        json.append("]");
        if (withRelated)
        {
            json.append(", related: [");
            for (int i = 0; i < BOOKS_COUNT; i++)
            {
                appendValue(json, createBook(true, false), false, (i < BOOKS_COUNT - 1));
            }
            json.append("]");
        }
        json.append("}");

        return json.toString();
    }


    private static String createAuthor()
    {
        StringBuilder json = new StringBuilder();

        json.append("{");
        appendKeyValue(json, "firstname", AUTHOR_FIRSTNAME, true, true);
        appendKeyValue(json, "surname", AUTHOR_SURNAME, true, true);
        appendKeyValue(json, "bestseller", createBook(false, false), false, false);
        json.append("}");

        return json.toString();
    }


    private static void appendKeyValue(StringBuilder json, String key, String value, boolean quote, boolean goon)
    {
        json.append(key).append(": ");
        if (quote)
        {
            json.append("\"");
        }
        json.append(value);
        if (quote)
        {
            json.append("\"");
        }
        if (goon)
        {
            json.append(", ");
        }
    }


    private static void appendValue(StringBuilder json, String value, boolean quote, boolean goon)
    {
        if (quote)
        {
            json.append("\"");
        }
        json.append(value);
        if (quote)
        {
            json.append("\"");
        }
        if (goon)
        {
            json.append(", ");
        }
    }
}
