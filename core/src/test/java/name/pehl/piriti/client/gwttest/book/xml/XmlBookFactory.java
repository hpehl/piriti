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


    /**
     * Creates books with author and related books.
     * 
     * @return
     */
    public static Document createBooks()
    {
        Document document = XMLParser.createDocument();
        Element books = document.createElement(BOOKS);
        for (int i = 0; i < BOOKS_COUNT; i++)
        {
            books.appendChild(createBookElement(document, "book", true, true));
        }

        document.appendChild(books);
        return document;
    }


    /**
     * Creates a book with author and related books.
     * 
     * @return
     */
    public static Document createBook()
    {
        Document document = XMLParser.createDocument();
        document.appendChild(createBookElement(document, "book", true, true));
        return document;
    }


    /**
     * Create a book. Depending on the parameters {@code withAuthor} and
     * {@code withRelated} the author and related elements are also generated.
     * 
     * @param withAuthor
     * @param withRelated
     * @return
     */
    private static Element createBookElement(Document document, String elementName, boolean withAuthor,
            boolean withRelated)
    {
        Element book = document.createElement(elementName);

        createElementAndAppend(document, book, "isbn", ISBN);
        createElementAndAppend(document, book, "pages", String.valueOf(PAGES));
        createElementAndAppend(document, book, "title", TITLE);
        if (withAuthor)
        {
            createAuthorElement(document, book, "author");
        }
        createReviewsElement(document, book, "reviews");
        if (withRelated)
        {
            Element related = document.createElement("related");
            for (int i = 0; i < BOOKS_COUNT; i++)
            {
                related.appendChild(createBookElement(document, "book", true, false));
            }
            book.appendChild(related);
        }

        return book;
    }


    private static void createAuthorElement(Document document, Element bookElement, String elementName)
    {
        Element author = document.createElement(elementName);
        createElementAndAppend(document, author, "firstname", AUTHOR_FIRSTNAME);
        createElementAndAppend(document, author, "surname", AUTHOR_SURNAME);
        Element bestseller = document.createElement("bestseller");
        bestseller.appendChild(createBookElement(document, "book", false, false));
        author.appendChild(bestseller);
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
