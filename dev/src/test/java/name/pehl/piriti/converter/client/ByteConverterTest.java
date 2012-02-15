package name.pehl.piriti.converter.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 19 $
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
        Byte result = underTest.convert(null);
        assertNull(result);
    }


    @Test
    public void testConvertEmpty()
    {
        Byte result = underTest.convert("");
        assertNull(result);
    }


    @Test
    public void testConvertBlank()
    {
        Byte result = underTest.convert("    ");
        assertNull(result);
    }


    @Test
    public void testConvertFoo()
    {
        Byte result = underTest.convert("foo");
        assertNull(result);
    }


    @Test
    public void testConvertPositive()
    {
        Byte result = underTest.convert("23");
        assertEquals(23, result.byteValue());
    }


    @Test
    public void testConvertNegative()
    {
        Byte result = underTest.convert("-23");
        assertEquals(-23, result.byteValue());
    }


    @Test
    public void testConvertLessThanMin()
    {
        Byte result = underTest.convert("-129");
        assertNull(result);
    }


    @Test
    public void testConvertMoreThanMax()
    {
        Byte result = underTest.convert("128");
        assertNull(result);
    }
}
