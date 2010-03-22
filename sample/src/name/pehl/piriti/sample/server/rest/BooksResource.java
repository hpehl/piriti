package name.pehl.piriti.sample.server.rest;

import java.util.ArrayList;
import java.util.List;

import name.pehl.piriti.sample.server.model.Author;
import name.pehl.piriti.sample.server.model.Book;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

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


    @Get
    public List<Book> list()
    {
        return books;
    }

    static class BooksGenerator
    {
        List<Book> generateBooks()
        {
            List<Book> books = new ArrayList<Book>();
            Author johnIrving = new Author("John", "Irving");
            Book hotelNewHampshire = new Book("978-0345417954", "The Hotel New Hampshire");
            hotelNewHampshire.setPages(432);
            hotelNewHampshire.setAuthor(johnIrving);
            hotelNewHampshire.addReview("A hectic gaudy saga with the verve of a Marx Brothers movie.");
            hotelNewHampshire
                    .addReview("Rejoice! John Irving has written another book according to your world. You must read this book.");
            hotelNewHampshire.addReview("Spellbinding, intensely human, a high-wire act of dazzling virtuosity.");
            books.add(hotelNewHampshire);
            return books;
        }
    }
}
