package name.pehl.piriti.client.namespaces;

import name.pehl.piriti.xml.client.Xml;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */

public class Player
{
    // ---------------------------------------------------- xml reader / writer

    public interface PlayerXmlReader extends XmlReader<Player>
    {
    }

    public static final PlayerXmlReader XML_READER = GWT.create(PlayerXmlReader.class);

    public interface PlayerXmlWriter extends XmlWriter<Player>
    {
    }

    public static final PlayerXmlWriter XML_WRITER = GWT.create(PlayerXmlWriter.class);

    // ------------------------------------------------------------------- data

    @Xml("@foo:gender")
    Gender gender;

    @Xml("@age")
    int age;

    @Xml("foo:firstname/text()")
    String firstname;

    @Xml("foo:surname/text()")
    String surname;

    @Xml("bar:address/text()")
    String address;

    @Xml("bar:address/@bar:type")
    String addressType;

    @Xml("bar:address/@foo:valid")
    boolean validAddress;
}
