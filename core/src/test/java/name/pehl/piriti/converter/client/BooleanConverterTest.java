package name.pehl.piriti.converter.client;

import static org.junit.Assert.*;

import name.pehl.piriti.converter.client.BooleanConverter;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $LastChangedBy: harald.pehl $ 
 * @version $LastChangedRevision: 19 $ 
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
