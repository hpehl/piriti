package name.pehl.piriti.sample.server.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author $Author$
 * @version $Date$ $Revision: 131
 *          $
 */
public class Book
{
    private String isbn;
    private int pages;
    private String title;
    private Author author;
    private List<String> reviews;


    // ----------------------------------------------------------- constructors

    public Book(String isbn)
    {
        this(isbn, null);
    }


    public Book(String isbn, String title)
    {
        super();
        this.isbn = isbn;
        this.title = title;
        this.reviews = new ArrayList<String>();
    }


    // --------------------------------------------------------- public methods

    @Override
    public String toString()
    {
        return new StringBuilder().append("Book [").append(isbn).append(", ").append(title).append("]").toString();
    }


    // ------------------------------------------------------------- properties

    public int getPages()
    {
        return pages;
    }


    public void setPages(int pages)
    {
        this.pages = pages;
    }


    public String getTitle()
    {
        return title;
    }


    public void setTitle(String title)
    {
        this.title = title;
    }


    public Author getAuthor()
    {
        return author;
    }


    public void setAuthor(Author author)
    {
        this.author = author;
    }


    public List<String> getReviews()
    {
        return reviews;
    }


    public void addReview(String review)
    {
        this.reviews.add(review);
    }


    public String getIsbn()
    {
        return isbn;
    }
}
