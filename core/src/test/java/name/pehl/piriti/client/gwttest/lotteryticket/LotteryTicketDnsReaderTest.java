package name.pehl.piriti.client.gwttest.lotteryticket;

import name.pehl.totoe.client.Document;
import name.pehl.totoe.client.XmlParser;

/**
 * @author $Author$
 * @version $Date$ $Revision: 295
 *          $
 */
public class LotteryTicketDnsReaderTest extends AbstractLotteryTicketTest
{
    // ------------------------------------------------------------- read tests

    /**
     * Test default namespace
     */
    public void testDns()
    {
        String xml = LotteryTicketResources.INSTANCE.lotteryTicketDns().getText();
        Document document = new XmlParser().parse(xml, LotteryTicketResources.DNS);
        LotteryTicketDns lt = LotteryTicketDns.XML_READER.read(document);
        assertLotteryTicket(lt);
    }


    /**
     * Test nested namespace
     */
    public void testDnsNns()
    {
        String xml = LotteryTicketResources.INSTANCE.lotteryTicketDnsNns().getText();
        Document document = new XmlParser().parse(xml, LotteryTicketResources.DNS);
        LotteryTicketDns lt = LotteryTicketDns.XML_READER.read(document);
        assertLotteryTicket(lt);
    }

    // ------------------------------------------------------------ writer tests

    // NYI
}
