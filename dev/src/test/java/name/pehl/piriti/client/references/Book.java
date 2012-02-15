package name.pehl.piriti.client.references;

import java.util.List;

import name.pehl.piriti.commons.client.Mapping;
import name.pehl.piriti.commons.client.Mappings;
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

    @Mappings(@Mapping(value = "extraInfoOfLastRelatedBook", path = "@.related[2].extraInfo"))    
    public interface BookJsonReader extends JsonReader<Book> {}
    public static final BookJsonReader JSON_READER = GWT.create(BookJsonReader.class);

    public interface BookJsonWriter extends JsonWriter<Book> {}
    public static final BookJsonWriter JSON_WRITER = GWT.create(BookJsonWriter.class);
    
    // ---------------------------------------------------- xml reader / writer

    @Mappings({@Mapping(value = "isbn", path = "@isbn"), 
        @Mapping(value = "reviews", path = "reviews/review"),
        @Mapping(value = "related", path = "related/book"),
        @Mapping(value = "extraInfoOfLastRelatedBook", path = "//related/book[3]/extraInfo/text()")})    
    public interface BookXmlReader extends XmlReader<Book> {}
    public static final BookXmlReader XML_READER = GWT.create(BookXmlReader.class);

    @Mappings({@Mapping(value = "isbn", path = "@isbn"), 
        @Mapping(value = "reviews", path = "reviews/review"),
        @Mapping(value = "related", path = "related/book"),
        @Mapping(value = "extraInfoOfLastRelatedBook", path = "//related/book[3]/extraInfo/text()")})    
    public interface BookXmlWriter extends XmlWriter<Book> {}
    public static final BookXmlWriter XML_WRITER = GWT.create(BookXmlWriter.class);

    // ------------------------------------------------------------------- data

    String isbn;
    int pages;
    String title;
    Author author;
    List<String> reviews;
    List<Book> related;
    String extraInfoOfLastRelatedBook;
}
