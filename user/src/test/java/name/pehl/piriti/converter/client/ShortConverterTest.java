package name.pehl.piriti.converter.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 19 $
 */
public class ShortConverterTest
{
    private ShortConverter underTest;


    @Before
    public void setUp() throws Exception
    {
        underTest = new ShortConverter();
    }


    @Test
    public void testConvertNull()
    {
        Short result = underTest.convert(null);
        assertNull(result);
    }


    @Test
    public void testConvertEmpty()
    {
        Short result = underTest.convert("");
        assertNull(result);
    }


    @Test
    public void testConvertBlank()
    {
        Short result = underTest.convert("    ");
        assertNull(result);
    }


    @Test
    public void testConvertFoo()
    {
        Short result = underTest.convert("foo");
        assertNull(result);
    }


    @Test
    public void testConvertPositive()
    {
        Short result = underTest.convert("23");
        assertEquals(23, result.shortValue());
    }


    @Test
    public void testConvertNegative()
    {
        Short result = underTest.convert("-23");
        assertEquals(-23, result.shortValue());
    }


    @Test
    public void testConvertLessThanMin()
    {
        Short result = underTest.convert("-32769");
        assertNull(result);
    }


    @Test
    public void testConvertMoreThanMax()
    {
        Short result = underTest.convert("32768");
        assertNull(result);
    }
}
