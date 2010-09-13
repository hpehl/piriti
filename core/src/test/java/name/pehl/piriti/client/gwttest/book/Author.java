package name.pehl.piriti.client.gwttest.book;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.json.JsonWriter;
import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlReader;
import name.pehl.piriti.client.xml.XmlWriter;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-02-15 16:03:08 +0100 (Mo, 15 Feb 2010) $ $Revision: 131
 *          $
 */
public class Author
{
    // --------------------------------------------------- json reader / writer

    // @formatter:off
    public interface AuthorJsonWriter extends JsonWriter<Author> {}
    public static final AuthorJsonWriter JSON_WRITER = GWT.create(AuthorJsonWriter.class);

    public interface AuthorJsonReader extends JsonReader<Author> {}
    public static final AuthorJsonReader JSON_READER = GWT.create(AuthorJsonReader.class);
    // @formatter:on

    // ---------------------------------------------------- xml reader / writer

    // @formatter:off
    public interface AuthorXmlReader extends XmlReader<Author> {}
    public static final AuthorXmlReader XML_READER = GWT.create(AuthorXmlReader.class);

    public interface AuthorXmlWriter extends XmlWriter<Author> {}
    public static final AuthorXmlWriter XML_WRITER = GWT.create(AuthorXmlWriter.class);
    // @formatter:on

    // ------------------------------------------------------------------- data

    @JsonField
    @XmlField
    String firstname;

    @JsonField
    @XmlField
    String surname;

    @JsonField
    @XmlField("bestseller/book")
    Book bestseller;
}
