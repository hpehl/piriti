package name.pehl.piriti.converter.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.DecimalFormat;
import java.text.ParseException;

import name.pehl.piriti.converter.client.IntegerConverter;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 19 $
 */
public class IntegerConverterTest extends IntegerConverter
{
    private IntegerConverter underTest;


    @Override
    protected Integer parseInteger(String value, String format)
    {
        DecimalFormat parser = new DecimalFormat(format);
        try
        {
            Integer result = (Integer) parser.parse(value);
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
        underTest = new IntegerConverterTest();
    }


    @Test
    public void testConvertNull()
    {
        Integer result = underTest.convert(null, null);
        assertNull(result);
    }


    @Test
    public void testConvertEmpty()
    {
        Integer result = underTest.convert("", null);
        assertNull(result);
    }


    @Test
    public void testConvertBlank()
    {
        Integer result = underTest.convert("    ", null);
        assertNull(result);
    }


    @Test
    public void testConvertFoo()
    {
        Integer result = underTest.convert("foo", null);
        assertNull(result);
    }


    @Test
    public void testConvertPositive()
    {
        Integer result = underTest.convert("23", null);
        assertEquals(23, result.intValue());
    }


    @Test
    public void testConvertNegative()
    {
        Integer result = underTest.convert("-23", null);
        assertEquals(-23, result.intValue());
    }


    @Test
    public void testConvertLessThanMin()
    {
        Integer result = underTest.convert("-2147483649", null);
        assertNull(result);
    }


    @Test
    public void testConvertMoreThanMax()
    {
        Integer result = underTest.convert("2147483648", null);
        assertNull(result);
    }
}
