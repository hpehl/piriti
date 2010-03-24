package name.pehl.piriti.sample.server.rest;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import name.pehl.piriti.sample.server.model.Author;
import name.pehl.piriti.sample.server.model.Book;
import name.pehl.piriti.sample.server.model.BooksGenerator;

import org.json.JSONException;
import org.json.JSONWriter;
import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * @author $Author$
 * @version $Date$ $Revision: 272
 *          $
 */
public class BooksResource extends ServerResource
{
    private final List<Book> books;


    public BooksResource()
    {
        BooksGenerator bg = new BooksGenerator();
        books = bg.generateBooks();
    }


    @Get("json")
    public Representation listAsJson() throws JSONException
    {
        StringWriter buffer = new StringWriter();
        JSONWriter writer = new JSONWriter(buffer);
        writer.array();
        for (Book book : books)
        {
            writer.object().key("isbn").value(book.getIsbn()).key("pages").value(book.getPages()).key("title").value(
                    book.getTitle()).key("author").object().key("firstname").value(book.getAuthor().getFirstname())
                    .key("surname").value(book.getAuthor().getSurname()).endObject();
            writer.key("reviews").array();
            for (String review : book.getReviews())
            {
                writer.value(review);
            }
            writer.endArray().endObject();
        }
        writer.endArray();
        JsonRepresentation representation = new JsonRepresentation(buffer.toString());
        return representation;
    }


    @Get("xml")
    public Representation listAsXml() throws IOException
    {
        DomRepresentation representation = new DomRepresentation(MediaType.TEXT_XML);
        Document document = representation.getDocument();
        Element root = document.createElement("books");
        document.appendChild(root);

        for (Book book : books)
        {
            Element bookElement = document.createElement("book");
            createAndAppendElement(document, bookElement, "isbn", book.getIsbn());
            createAndAppendElement(document, bookElement, "pages", String.valueOf(book.getPages()));
            createAndAppendElement(document, bookElement, "title", book.getTitle());

            Author author = book.getAuthor();
            Element authorElement = document.createElement("author");
            createAndAppendElement(document, authorElement, "firstname", author.getFirstname());
            createAndAppendElement(document, authorElement, "surname", author.getSurname());
            bookElement.appendChild(authorElement);

            List<String> reviews = book.getReviews();
            Element reviewsElement = document.createElement("reviews");
            for (String review : reviews)
            {
                Element reviewElement = document.createElement("review");
                reviewElement.appendChild(document.createTextNode(review));
                reviewsElement.appendChild(reviewElement);
            }
            bookElement.appendChild(reviewsElement);

            root.appendChild(bookElement);
        }

        return representation;
    }


    private void createAndAppendElement(Document document, Element parent, String name, String value)
    {
        Element element = document.createElement(name);
        Text textNode = document.createTextNode(value);
        element.appendChild(textNode);
        parent.appendChild(element);
    }
}
