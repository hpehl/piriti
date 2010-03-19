package name.pehl.piriti.client.book.json;

import name.pehl.piriti.client.book.BookFactory;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 131 $
 */
public final class JsonBookFactory implements BookFactory
{
    private JsonBookFactory()
    {
    }


    public static String createJson()
    {
        StringBuilder json = new StringBuilder();

        json.append("{");
        appendKeyValue(json, "isbn", ISBN, true, true);
        appendKeyValue(json, "pages", String.valueOf(PAGES), false, true);
        appendKeyValue(json, "title", TITLE, true, true);
        json.append("author: {");
        appendKeyValue(json, "firstname", AUTHOR_FIRSTNAME, true, true);
        appendKeyValue(json, "surname", AUTHOR_SURNAME, true, false);
        json.append("}, ");
        json.append("reviews: [");
        for (int i = 0; i < REVIEWS.length; i++)
        {
            appendValue(json, REVIEWS[i], true, (i < REVIEWS.length - 1));
        }
        json.append("]}");

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
