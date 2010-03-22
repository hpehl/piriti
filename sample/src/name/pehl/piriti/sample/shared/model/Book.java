package name.pehl.piriti.sample.shared.model;

import java.util.ArrayList;
import java.util.List;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.client.xml.XmlField;

/**
 * @author $Author$
 * @version $Date$ $Revision: 131
 *          $
 */
public class Book
{
    @XmlField
    @JsonField
    String isbn;

    @XmlField
    @JsonField
    int pages;

    @XmlField
    @JsonField
    String title;

    @XmlField
    @JsonField
    Author author;

    @XmlField("reviews/review")
    @JsonField
    List<String> reviews;


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


    @Override
    public String toString()
    {
        return new StringBuilder().append("Book [").append(isbn).append(", ").append(title).append("]").toString();
    }
}
