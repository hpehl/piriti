package name.pehl.piriti.client.gwttest.book.xml;

import name.pehl.piriti.client.gwttest.book.BookFactory;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Text;
import com.google.gwt.xml.client.XMLParser;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 135 $
 */
public final class XmlBookFactory implements BookFactory
{
    private XmlBookFactory()
    {
    }


    public static Document createDocument()
    {
        Document document = XMLParser.createDocument();
        Element book = document.createElement("book");

        createElementAndAppend(document, book, "isbn", ISBN);
        createElementAndAppend(document, book, "pages", String.valueOf(PAGES));
        createElementAndAppend(document, book, "title", TITLE);

        createAuthor(document, book, "author");
        createReviews(document, book, "reviews");

        document.appendChild(book);
        return document;
    }


    private static void createAuthor(Document document, Element bookElement, String elementName)
    {
        Element author = document.createElement(elementName);
        createElementAndAppend(document, author, "firstname", AUTHOR_FIRSTNAME);
        createElementAndAppend(document, author, "surname", AUTHOR_SURNAME);
        bookElement.appendChild(author);
    }


    private static void createReviews(Document document, Element bookElement, String elementName)
    {
        Element reviews = document.createElement(elementName);
        createElementsAndAppend(document, reviews, "review", REVIEWS);
        bookElement.appendChild(reviews);
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
