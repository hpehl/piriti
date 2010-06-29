package name.pehl.piriti.client.gwttest.namespace;

import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */

public class Player
{
    public interface PlayerXmlReader extends XmlReader<Player>
    {
    }

    public static final PlayerXmlReader XML = GWT.create(PlayerXmlReader.class);
    // TODO What happens if we don't register namespaces for nested POJOs?
    static
    {
        XML.registerNamespace("foo", "http://code.google.com/p/piriti/foo");
        XML.registerNamespace("bar", "http://code.google.com/p/piriti/bar");
    }

    @XmlField("@foo:gender")
    Gender gender;

    @XmlField("@age")
    int age;

    @XmlField("foo:firstname/text()")
    String firstname;

    @XmlField("foo:surname/text()")
    String surname;

    @XmlField("bar:address/text()")
    String address;

    @XmlField("bar:address/@bar:type")
    String addressType;

    @XmlField("bar:address/@foo:valid")
    boolean validAddress;
}
