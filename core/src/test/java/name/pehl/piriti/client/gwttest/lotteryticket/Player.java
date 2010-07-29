package name.pehl.piriti.client.gwttest.lotteryticket;

import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlReader;
import name.pehl.piriti.client.xml.XmlWriter;

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
