package name.pehl.piriti.client.gwttest.lotteryticket;

import static name.pehl.piriti.client.gwttest.lotteryticket.LotteryTicketResources.*;

import java.util.Date;
import java.util.List;

import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class LotteryTicketDns
{
    public interface LotteryTicketReader extends XmlReader<LotteryTicketDns>
    {
    }

    public static final LotteryTicketReader XML = GWT.create(LotteryTicketReader.class);

    @XmlField(value = "@date", format = "dd.MM.yyyy")
    Date date;

    @XmlField("foo:player")
    Player player;

    @XmlField(DNS_PREFIX + ":numbers/@game")
    String game;

    @XmlField(DNS_PREFIX + ":numbers/" + DNS_PREFIX + ":number")
    List<Integer> numbers;
}
