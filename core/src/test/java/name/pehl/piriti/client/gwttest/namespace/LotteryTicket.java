package name.pehl.piriti.client.gwttest.namespace;

import java.util.Date;
import java.util.List;

import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class LotteryTicket
{
    public interface LotteryTicketReader extends XmlReader<LotteryTicket>
    {
    }

    public static final LotteryTicketReader XML = GWT.create(LotteryTicketReader.class);
    static
    {
        XML.registerNamespace("foo", "http://code.google.com/p/piriti/foo");
        XML.registerNamespace("bar", "http://code.google.com/p/piriti/bar");
    }

    @XmlField(value = "@date", format = "dd.MM.yyyy")
    Date date;

    @XmlField("foo:player")
    Player player;

    @XmlField("numbers/@game")
    String game;

    @XmlField("numbers/number")
    List<Integer> numbers;
}
