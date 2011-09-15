package name.pehl.piriti.converter.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 19 $
 */
public class LongConverterTest
{
    private LongConverter underTest;


    @Before
    public void setUp() throws Exception
    {
        underTest = new LongConverter();
    }


    @Test
    public void testConvertNull()
    {
        Long result = underTest.convert(null);
        assertNull(result);
    }


    @Test
    public void testConvertEmpty()
    {
        Long result = underTest.convert("");
        assertNull(result);
    }


    @Test
    public void testConvertBlank()
    {
        Long result = underTest.convert("    ");
        assertNull(result);
    }


    @Test(expected = NumberFormatException.class)
    public void testConvertFoo()
    {
        underTest.convert("foo");
    }


    @Test
    public void testConvertPositive()
    {
        Long result = underTest.convert("23");
        assertEquals(23, result.longValue());
    }


    @Test
    public void testConvertNegative()
    {
        Long result = underTest.convert("-23");
        assertEquals(-23, result.longValue());
    }


    @Test(expected = NumberFormatException.class)
    public void testConvertLessThanMin()
    {
        underTest.convert("-9223372036854775809");
    }


    @Test(expected = NumberFormatException.class)
    public void testConvertMoreThanMax()
    {
        underTest.convert("9223372036854775808");
    }
}
