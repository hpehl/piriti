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


    public static String createBook()
    {
        StringBuilder json = new StringBuilder();

        json.append("{");
        json.append("isbn: ").append(ISBN).append(", ");
        json.append("pages: ").append(PAGES).append(", ");
        json.append("title: ").append(TITLE).append(", ");
        json.append("author {");
        json.append("firstname: ").append(AUTHOR_FIRSTNAME).append(", ");
        json.append("surname: ").append(AUTHOR_SURNAME).append(", ");
        json.append("}, ");
        json.append("reviews [");
        for (int i = 0; i < REVIEWS.length; i++)
        {
            json.append(REVIEWS[i]);
            if (i < REVIEWS.length - 1)
            {
                json.append(", ");
            }
        }
        json.append("]}");

        return json.toString();
    }
}
