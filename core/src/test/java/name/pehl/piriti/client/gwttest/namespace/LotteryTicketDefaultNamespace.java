package name.pehl.piriti.client.gwttest.namespace;

import static name.pehl.piriti.client.gwttest.namespace.LotteryTicketResources.*;

import java.util.Date;
import java.util.List;

import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class LotteryTicketDefaultNamespace
{
    public interface LotteryTicketReader extends XmlReader<LotteryTicketDefaultNamespace>
    {
    }

    public static final LotteryTicketReader XML = GWT.create(LotteryTicketReader.class);

    @XmlField(value = "@date", format = "dd.MM.yyyy")
    Date date;

    @XmlField("foo:player")
    Player player;

    @XmlField(DNS + ":numbers/@game")
    String game;

    @XmlField(DNS + ":numbers/" + DNS + ":number")
    List<Integer> numbers;
}
