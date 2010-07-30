package name.pehl.piriti.client.gwttest.lotteryticket;

import static name.pehl.piriti.client.gwttest.lotteryticket.LotteryTicketResources.*;
import name.pehl.piriti.client.gwttest.AbstractPiritiTest;

/**
 * @author $Author$
 * @version $Date$ $Revision: 295
 *          $
 */
public abstract class AbstractLotteryTicketTest extends AbstractPiritiTest
{
    protected void assertLotteryTicket(LotteryTicketDns lt)
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


    protected void assertLotteryTicket(LotteryTicket lt)
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
