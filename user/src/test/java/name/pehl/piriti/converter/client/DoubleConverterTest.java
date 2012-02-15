package name.pehl.piriti.converter.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 19 $
 */
public class DoubleConverterTest
{
    private DoubleConverter underTest;


    @Before
    public void setUp() throws Exception
    {
        underTest = new DoubleConverter();
    }


    @Test
    public void testConvertNull()
    {
        Double result = underTest.convert(null);
        assertNull(result);
    }


    @Test
    public void testConvertEmpty()
    {
        Double result = underTest.convert("");
        assertNull(result);
    }


    @Test
    public void testConvertBlank()
    {
        Double result = underTest.convert("    ");
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
        Double result = underTest.convert("23");
        assertEquals(23.0, result.doubleValue(), .0);
        result = underTest.convert("23.123");
        assertEquals(23.123, result.doubleValue(), .0);
    }


    @Test
    public void testConvertNegative()
    {
        Double result = underTest.convert("-23");
        assertEquals(-23.0, result.doubleValue(), .0);
        result = underTest.convert("-23.456");
        assertEquals(-23.456, result.doubleValue(), .0);
    }
}
