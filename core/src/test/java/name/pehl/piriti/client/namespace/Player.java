package name.pehl.piriti.client.namespace;

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

    @XmlField("/@vip")
    boolean vip;

    @XmlField("/@foo:gender")
    Gender gender;

    @XmlField("/@bar:age")
    int age;

    @XmlField("foo:firstname")
    String firstname;

    @XmlField("foo:surname")
    String surname;

    @XmlField("bar:address")
    String address;
}
