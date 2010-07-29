package name.pehl.piriti.client.gwttest.lotteryticket;

import static name.pehl.piriti.client.gwttest.lotteryticket.LotteryTicketResources.DNS_PREFIX;

import java.util.Date;
import java.util.List;

import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlReader;
import name.pehl.piriti.client.xml.XmlWriter;

import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class LotteryTicketDns
{
    // ---------------------------------------------------- xml reader / writer

    public interface LotteryTicketReader extends XmlReader<LotteryTicketDns>
    {
    }

    public static final LotteryTicketReader XML_READER = GWT.create(LotteryTicketReader.class);

    public interface LotteryTicketWriter extends XmlWriter<LotteryTicketDns>
    {
    }

    public static final LotteryTicketWriter XML_WRITER = GWT.create(LotteryTicketWriter.class);

    // ------------------------------------------------------------------- data

    @XmlField(value = "@date", format = "dd.MM.yyyy")
    Date date;

    @XmlField("foo:player")
    Player player;

    @XmlField(DNS_PREFIX + ":numbers/@game")
    String game;

    @XmlField(DNS_PREFIX + ":numbers/" + DNS_PREFIX + ":number")
    List<Integer> numbers;
}
