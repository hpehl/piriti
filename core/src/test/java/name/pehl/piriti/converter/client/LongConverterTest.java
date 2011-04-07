package name.pehl.piriti.converter.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.DecimalFormat;
import java.text.ParseException;

import name.pehl.piriti.converter.client.LongConverter;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 19 $
 */
public class LongConverterTest extends LongConverter
{
    private LongConverter underTest;


    @Override
    protected Long parseLong(String value, String format)
    {
        DecimalFormat parser = new DecimalFormat(format);
        try
        {
            Long result = (Long) parser.parse(value);
            return result;
        }
        catch (ParseException e)
        {
            throw new NumberFormatException(e.getMessage());
        }
    }


    @Before
    public void setUp() throws Exception
    {
        underTest = new LongConverterTest();
    }


    @Test
    public void testConvertNull()
    {
        Long result = underTest.convert(null, null);
        assertNull(result);
    }


    @Test
    public void testConvertEmpty()
    {
        Long result = underTest.convert("", null);
        assertNull(result);
    }


    @Test
    public void testConvertBlank()
    {
        Long result = underTest.convert("    ", null);
        assertNull(result);
    }


    @Test
    public void testConvertFoo()
    {
        Long result = underTest.convert("foo", null);
        assertNull(result);
    }


    @Test
    public void testConvertPositive()
    {
        Long result = underTest.convert("23", null);
        assertEquals(23, result.longValue());
    }


    @Test
    public void testConvertNegative()
    {
        Long result = underTest.convert("-23", null);
        assertEquals(-23, result.longValue());
    }


    @Test
    public void testConvertLessThanMin()
    {
        Long result = underTest.convert("-9223372036854775809", null);
        assertNull(result);
    }


    @Test
    public void testConvertMoreThanMax()
    {
        Long result = underTest.convert("9223372036854775808", null);
        assertNull(result);
    }
}
