package name.pehl.piriti.converter.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 19 $
 */
public class FloatConverterTest
{
    private FloatConverter underTest;


    @Before
    public void setUp() throws Exception
    {
        underTest = new FloatConverter();
    }


    @Test
    public void testConvertNull()
    {
        Float result = underTest.convert(null);
        assertNull(result);
    }


    @Test
    public void testConvertEmpty()
    {
        Float result = underTest.convert("");
        assertNull(result);
    }


    @Test
    public void testConvertBlank()
    {
        Float result = underTest.convert("    ");
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
        Float result = underTest.convert("23");
        assertEquals(23.0, result.floatValue(), .1);
        result = underTest.convert("23.123");
        assertEquals(23.123, result.floatValue(), .1);
    }


    @Test
    public void testConvertNegative()
    {
        Float result = underTest.convert("-23");
        assertEquals(-23.0, result.floatValue(), .1);
        result = underTest.convert("-23.456");
        assertEquals(-23.456, result.floatValue(), .1);
    }
}
