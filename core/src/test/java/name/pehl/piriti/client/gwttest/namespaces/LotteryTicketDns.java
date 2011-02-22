package name.pehl.piriti.client.gwttest.namespaces;

import static name.pehl.piriti.client.gwttest.namespaces.LotteryTicketResources.DNS_PREFIX;

import java.util.Date;
import java.util.List;

import name.pehl.piriti.client.xml.Xml;
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

    @Xml(value = "@date", format = "dd.MM.yyyy")
    Date date;

    @Xml("foo:player")
    Player player;

    @Xml(DNS_PREFIX + ":numbers/@game")
    String game;

    @Xml(DNS_PREFIX + ":numbers/" + DNS_PREFIX + ":number")
    List<Integer> numbers;
}
