package name.pehl.piriti.sample.client.model;

import java.util.List;

import name.pehl.piriti.client.json.Json;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.xml.Xml;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 131
 *          $
 */
// @formatter:off
public class Book
{
    public interface BookJsonReader extends JsonReader<Book> {}
    public static final BookJsonReader JSON = GWT.create(BookJsonReader.class);

    public interface BookXmlReader extends XmlReader<Book> {}
    public static final BookXmlReader XML = GWT.create(BookXmlReader.class);

    @Xml @Json String isbn;
    @Xml @Json int pages;
    @Xml @Json String title;
    @Xml @Json Author author;
    @Xml("reviews/review") @Json List<String> reviews;
}
