package name.pehl.gwt.piriti.client.converter;

import static org.junit.Assert.*;

import name.pehl.gwt.piriti.client.converter.BooleanConverter;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $LastChangedBy:$ 
 * @version $LastChangedRevision:$ 
 */
public class BooleanConverterTest
{
    private BooleanConverter underTest;


    @Before
    public void setUp() throws Exception
    {
        underTest = new BooleanConverter();
    }


    @Test
    public void testConvertNull()
    {
        Boolean result = underTest.convert(null, null);
        assertNull(result);
    }


    @Test
    public void testConvertEmpty()
    {
        Boolean result = underTest.convert("", null);
        assertNull(result);
    }


    @Test
    public void testConvertBlank()
    {
        Boolean result = underTest.convert("    ", null);
        assertNull(result);
    }


    @Test
    public void testConvertFoo()
    {
        Boolean result = underTest.convert("foo", null);
        assertFalse(result);
    }


    @Test
    public void testConvertFalse()
    {
        Boolean result = underTest.convert("false", null);
        assertFalse(result);
    }


    @Test
    public void testConvertTrue()
    {
        Boolean result = underTest.convert("true", null);
        assertTrue(result);
    }
}
