package name.pehl.piriti.client.gwttest.namespace;

import static name.pehl.piriti.client.gwttest.namespace.LotteryTicketResources.*;
import name.pehl.piriti.client.gwttest.AbstractPiritiTest;
import name.pehl.totoe.client.Document;
import name.pehl.totoe.client.XmlParser;

/**
 * @author $Author$
 * @version $Date$ $Revision: 295
 *          $
 */
public abstract class LotteryTicketReaderTest extends AbstractPiritiTest
{
    public void testDefaultNs()
    {
        String xml = LotteryTicketResources.INSTANCE.lotteryTicketDefaultNs().getText();
        XmlParser xmlParser = new XmlParser();
        Document document = xmlParser.parse(xml);
        LotteryTicketDefaultNamespace lt = LotteryTicketDefaultNamespace.XML.read(document);
        assertLotteryTicket(lt);
    }


    public void testNoDefaultNs()
    {
        String xml = LotteryTicketResources.INSTANCE.lotteryTicketNoDefaultNs().getText();
        XmlParser xmlParser = new XmlParser();
        Document document = xmlParser.parse(xml);
        LotteryTicket lt = LotteryTicket.XML.read(document);
        assertLotteryTicket(lt);
    }


    public void testDefaultAndNestedNs()
    {
        String xml = LotteryTicketResources.INSTANCE.lotteryTicketDefaultAndNestedNs().getText();
        XmlParser xmlParser = new XmlParser();
        Document document = xmlParser.parse(xml);
        LotteryTicketDefaultNamespace lt = LotteryTicketDefaultNamespace.XML.read(document);
        assertLotteryTicket(lt);
    }


    public void testNoDefaultAndNestedNs()
    {
        String xml = LotteryTicketResources.INSTANCE.lotteryTicketNoDefaultAndNestedNs().getText();
        XmlParser xmlParser = new XmlParser();
        Document document = xmlParser.parse(xml);
        LotteryTicket lt = LotteryTicket.XML.read(document);
        assertLotteryTicket(lt);
    }


    private void assertLotteryTicket(LotteryTicketDefaultNamespace lt)
    {
        assertNotNull(lt);
        assertEquals(DATE, lt.date);
        assertNotNull(lt.player);
        assertEquals(Gender.MALE, lt.player.gender);
        assertEquals(AGE, lt.player.age);
        assertEquals(FIRSTNAME, lt.player.firstname);
        assertEquals(SURNAME, lt.player.surname);
        assertEquals(ADDRESS, lt.player.address);
        assertEquals(ADDRESS_TYPE, lt.player.addressType);
        assertTrue(lt.player.validAddress);
        assertEquals(GAME, lt.game);
        assertNotNull(lt.numbers);
        assertFalse(lt.numbers.isEmpty());
        Integer[] numbers = lt.numbers.toArray(new Integer[] {});
        assertEquals(NUMBERS.length, numbers.length);
        for (int i = 0; i < NUMBERS.length; i++)
        {
            assertEquals(NUMBERS[i], numbers[i]);
        }
    }


    private void assertLotteryTicket(LotteryTicket lt)
    {
        assertNotNull(lt);
        assertEquals(DATE, lt.date);
        assertNotNull(lt.player);
        assertEquals(Gender.MALE, lt.player.gender);
        assertEquals(AGE, lt.player.age);
        assertEquals(FIRSTNAME, lt.player.firstname);
        assertEquals(SURNAME, lt.player.surname);
        assertEquals(ADDRESS, lt.player.address);
        assertEquals(ADDRESS_TYPE, lt.player.addressType);
        assertTrue(lt.player.validAddress);
        assertEquals(GAME, lt.game);
        assertNotNull(lt.numbers);
        assertFalse(lt.numbers.isEmpty());
        Integer[] numbers = lt.numbers.toArray(new Integer[] {});
        assertEquals(NUMBERS.length, numbers.length);
        for (int i = 0; i < NUMBERS.length; i++)
        {
            assertEquals(NUMBERS[i], numbers[i]);
        }
    }
}
