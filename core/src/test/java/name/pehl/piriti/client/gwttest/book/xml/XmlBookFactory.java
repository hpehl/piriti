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


    public static Document createBooks()
    {
        Document document = XMLParser.createDocument();
        Element books = document.createElement(BOOKS);
        for (int i = 0; i < BOOKS_COUNT; i++)
        {
            books.appendChild(createBookElement(document, "book"));
        }

        document.appendChild(books);
        return document;
    }


    public static Document createBook()
    {
        Document document = XMLParser.createDocument();
        document.appendChild(createBookElement(document, "book"));
        return document;
    }


    private static Element createBookElement(Document document, String elementName)
    {
        Element book = document.createElement(elementName);

        createElementAndAppend(document, book, "isbn", ISBN);
        createElementAndAppend(document, book, "pages", String.valueOf(PAGES));
        createElementAndAppend(document, book, "title", TITLE);
        createAuthorElement(document, book, "author");
        createReviewsElement(document, book, "reviews");

        return book;
    }


    private static void createAuthorElement(Document document, Element bookElement, String elementName)
    {
        Element author = document.createElement(elementName);
        createElementAndAppend(document, author, "firstname", AUTHOR_FIRSTNAME);
        createElementAndAppend(document, author, "surname", AUTHOR_SURNAME);
        bookElement.appendChild(author);
    }


    private static void createReviewsElement(Document document, Element bookElement, String elementName)
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
