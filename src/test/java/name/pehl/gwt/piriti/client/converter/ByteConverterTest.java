package name.pehl.gwt.piriti.client.converter;

import static org.junit.Assert.*;

import name.pehl.gwt.piriti.client.converter.ByteConverter;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $LastChangedBy:$ 
 * @version $LastChangedRevision:$ 
 */
public class ByteConverterTest
{
    private ByteConverter underTest;


    @Before
    public void setUp() throws Exception
    {
        underTest = new ByteConverter();
    }


    @Test
    public void testConvertNull()
    {
        Byte result = underTest.convert(null, null);
        assertNull(result);
    }


    @Test
    public void testConvertEmpty()
    {
        Byte result = underTest.convert("", null);
        assertNull(result);
    }


    @Test
    public void testConvertBlank()
    {
        Byte result = underTest.convert("    ", null);
        assertNull(result);
    }


    @Test
    public void testConvertFoo()
    {
        Byte result = underTest.convert("foo", null);
        assertNull(result);
    }


    @Test
    public void testConvertPositive()
    {
        Byte result = underTest.convert("23", null);
        assertEquals(23, result.byteValue());
    }


    @Test
    public void testConvertNegative()
    {
        Byte result = underTest.convert("-23", null);
        assertEquals(-23, result.byteValue());
    }


    @Test
    public void testConvertLessThanMin()
    {
        Byte result = underTest.convert("-129", null);
        assertNull(result);
    }


    @Test
    public void testConvertMoreThanMax()
    {
        Byte result = underTest.convert("128", null);
        assertNull(result);
    }
}
