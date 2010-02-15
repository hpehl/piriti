package name.pehl.gwt.piriti.client.xml.book;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Text;
import com.google.gwt.xml.client.XMLParser;

/**
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public final class BookXmlFactory
{
    static final String ISBN = "978-0345417954";
    static final int PAGES = 432;
    static final String TITLE = "The Hotel New Hampshire";
    static final String AUTHOR_SURNAME = "Irving";
    static final String AUTHOR_FIRSTNAME = "John";
    static final String[] REVIEWS = new String[] {"A hectic gaudy saga with the verve of a Marx Brothers movie.",
            "Rejoice! John Irving has written another book according to your world. You must read this book.",
            "Spellbinding, intensely human, a high-wire act of dazzling virtuosity.",};


    private BookXmlFactory()
    {
    }


    public static Document createBookDocument(String elementName)
    {
        Document document = XMLParser.createDocument();
        Element bookElement = document.createElement(elementName);

        createElementAndAppend(document, bookElement, "isbn", ISBN);
        createElementAndAppend(document, bookElement, "pages", String.valueOf(PAGES));
        createElementAndAppend(document, bookElement, "title", TITLE);

        createAuthorElement(document, bookElement, "author");
        createReviewsList(document, bookElement, "reviews");

        document.appendChild(bookElement);
        return document;
    }


    private static void createAuthorElement(Document document, Element bookElement, String elementName)
    {
        Element authorElement = document.createElement(elementName);
        createElementAndAppend(document, authorElement, "firstname", AUTHOR_FIRSTNAME);
        createElementAndAppend(document, authorElement, "surname", AUTHOR_SURNAME);
        bookElement.appendChild(authorElement);
    }


    private static void createReviewsList(Document document, Element bookElement, String elementName)
    {
        Element reviewsElement = document.createElement(elementName);
        createElementsAndAppend(document, reviewsElement, "review", REVIEWS);
        bookElement.appendChild(reviewsElement);
    }


    private static void createElementsAndAppend(Document document, Element parent, String elementName, String... values)
    {
        if (values != null && values.length != 0)
        {
            for (String value : values)
            {
                createElementAndAppend(document, parent, elementName, value);
            }
        }
    }


    private static Element createElementAndAppend(Document document, Element parent, String elementName, String value)
    {
        Element element = document.createElement(elementName);
        Text text = document.createTextNode(value);
        element.appendChild(text);
        parent.appendChild(element);
        return element;
    }
}
