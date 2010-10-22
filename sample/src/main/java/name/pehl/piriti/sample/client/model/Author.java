package name.pehl.piriti.sample.client.model;

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
public class Author
{
    public interface AuthorJsonReader extends JsonReader<Author> {}
    public static final AuthorJsonReader JSON = GWT.create(AuthorJsonReader.class);

    public interface AuthorXmlReader extends XmlReader<Author> {}
    public static final AuthorXmlReader XML = GWT.create(AuthorXmlReader.class);

    @Xml @Json String firstname;
    @Xml @Json String surname;
}
