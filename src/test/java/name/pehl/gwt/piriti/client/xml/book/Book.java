package name.pehl.gwt.piriti.client.xml.book;

import java.util.List;

import name.pehl.gwt.piriti.client.xml.XmlField;
import name.pehl.gwt.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class Book
{
    interface BookReader extends XmlReader<Book>
    {
    }

    public static final BookReader XML = GWT.create(BookReader.class);

    @XmlField
    String isbn;
    @XmlField
    int pages;
    @XmlField
    String title;
    @XmlField
    Author author;
    @XmlField("reviews/review")
    List<String> reviews;
}
