package name.pehl.piriti.converter.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 19 $
 */
public class IntegerConverterTest
{
    private IntegerConverter underTest;


    @Before
    public void setUp() throws Exception
    {
        underTest = new IntegerConverter();
    }


    @Test
    public void testConvertNull()
    {
        Integer result = underTest.convert(null);
        assertNull(result);
    }


    @Test
    public void testConvertEmpty()
    {
        Integer result = underTest.convert("");
        assertNull(result);
    }


    @Test
    public void testConvertBlank()
    {
        Integer result = underTest.convert("    ");
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
        Integer result = underTest.convert("23");
        assertEquals(23, result.intValue());
    }


    @Test
    public void testConvertNegative()
    {
        Integer result = underTest.convert("-23");
        assertEquals(-23, result.intValue());
    }


    @Test(expected = NumberFormatException.class)
    public void testConvertLessThanMin()
    {
        underTest.convert("-2147483649");
    }


    @Test(expected = NumberFormatException.class)
    public void testConvertMoreThanMax()
    {
        underTest.convert("2147483648");
    }
}
