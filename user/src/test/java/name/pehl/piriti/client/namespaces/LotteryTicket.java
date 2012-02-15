package name.pehl.piriti.client.namespaces;

import java.util.Date;
import java.util.List;

import name.pehl.piriti.commons.client.Format;
import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class LotteryTicket
{
    // ---------------------------------------------------- xml reader / writer

    public interface LotteryTicketReader extends XmlReader<LotteryTicket>
    {
    }

    public static final LotteryTicketReader XML_READER = GWT.create(LotteryTicketReader.class);

    public interface LotteryTicketWriter extends XmlWriter<LotteryTicket>
    {
    }

    public static final LotteryTicketWriter XML_WRITER = GWT.create(LotteryTicketWriter.class);

    // ------------------------------------------------------------------- data

    @Path("@date")
    @Format("dd.MM.yyyy")
    Date date;

    @Path("foo:player")
    Player player;

    @Path("numbers/@game")
    String game;

    @Path("numbers/number")
    List<Integer> numbers;
}
