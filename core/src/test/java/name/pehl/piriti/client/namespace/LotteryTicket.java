package name.pehl.piriti.client.namespace;

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
    public interface LotteryTicketXmlReader extends XmlReader<LotteryTicket>
    {
    }

    public static final LotteryTicketXmlReader XML = GWT.create(LotteryTicketXmlReader.class);

    @XmlField(value = "/@foo:date", format = "dd.MM.yyyy")
    Date date;

    @XmlField("foo:player")
    Player player;

    @XmlField("numbers/number")
    List<Integer> numbers;
}
