package name.pehl.piriti.client.namespaces;

import name.pehl.piriti.commons.client.Path;
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

    @Path("@foo:gender")
    Gender gender;

    @Path("@age")
    int age;

    @Path("foo:firstname/text()")
    String firstname;

    @Path("foo:surname/text()")
    String surname;

    @Path("bar:address/text()")
    String address;

    @Path("bar:address/@bar:type")
    String addressType;

    @Path("bar:address/@foo:valid")
    boolean validAddress;
}
