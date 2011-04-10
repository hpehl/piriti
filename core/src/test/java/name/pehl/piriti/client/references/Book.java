package name.pehl.piriti.client.references;

import java.util.List;

import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-02-15 16:03:08 +0100 (Mo, 15 Feb 2010) $ $Revision: 131
 *          $
 */
// @formatter:off
public class Book
{
    // --------------------------------------------------- json reader / writer

    public interface BookJsonWriter extends JsonWriter<Book> {}
    public static final BookJsonWriter JSON_WRITER = GWT.create(BookJsonWriter.class);

    public interface BookJsonReader extends JsonReader<Book> {}
    public static final BookJsonReader JSON_READER = GWT.create(BookJsonReader.class);

    // ---------------------------------------------------- xml reader / writer

    public interface BookXmlReader extends XmlReader<Book> {}
    public static final BookXmlReader XML_READER = GWT.create(BookXmlReader.class);

    public interface BookXmlWriter extends XmlWriter<Book> {}
    public static final BookXmlWriter XML_WRITER = GWT.create(BookXmlWriter.class);

    // ------------------------------------------------------------------- data

    String isbn;
    int pages;
    String title;
    Author author;
    @Path("reviews/review") List<String> reviews;
    @Path("related/book") List<Book> related;
    
    @Path("@.related[2].extraInfo") 
//    @Path("//related/book[3]/extraInfo/text()") 
    String extraInfoOfLastRelatedBook;
}
