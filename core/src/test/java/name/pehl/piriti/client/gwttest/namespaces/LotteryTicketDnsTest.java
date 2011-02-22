package name.pehl.piriti.client.gwttest.namespaces;

import name.pehl.totoe.xml.client.Document;
import name.pehl.totoe.xml.client.XmlParser;

/**
 * @author $Author$
 * @version $Date$ $Revision: 295
 *          $
 */
public class LotteryTicketDnsTest extends AbstractLotteryTicketTest
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
