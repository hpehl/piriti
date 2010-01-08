package name.pehl.gwt.piriti.client.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import name.pehl.gwt.piriti.client.converter.ShortConverter;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $LastChangedBy:$ 
 * @version $LastChangedRevision:$ 
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
        Short result = underTest.convert(null, null);
        assertNull(result);
    }


    @Test
    public void testConvertEmpty()
    {
        Short result = underTest.convert("", null);
        assertNull(result);
    }


    @Test
    public void testConvertBlank()
    {
        Short result = underTest.convert("    ", null);
        assertNull(result);
    }


    @Test
    public void testConvertFoo()
    {
        Short result = underTest.convert("foo", null);
        assertNull(result);
    }


    @Test
    public void testConvertPositive()
    {
        Short result = underTest.convert("23", null);
        assertEquals(23, result.shortValue());
    }


    @Test
    public void testConvertNegative()
    {
        Short result = underTest.convert("-23", null);
        assertEquals(-23, result.shortValue());
    }


    @Test
    public void testConvertLessThanMin()
    {
        Short result = underTest.convert("-32769", null);
        assertNull(result);
    }


    @Test
    public void testConvertMoreThanMax()
    {
        Short result = underTest.convert("32768", null);
        assertNull(result);
    }
}
