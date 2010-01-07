package name.pehl.gwt.piriti.client.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.DecimalFormat;
import java.text.ParseException;

import name.pehl.gwt.piriti.client.converter.FloatConverter;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $Author:$
 * @version $Revision:$
 */
public class FloatConverterTest extends FloatConverter
{
    private FloatConverter underTest;


    @Override
    protected Float parseFloat(String value, String format)
    {
        DecimalFormat parser = new DecimalFormat(format);
        try
        {
            Float result = (Float) parser.parse(value);
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
        underTest = new FloatConverterTest();
    }


    @Test
    public void testConvertNull()
    {
        Float result = underTest.convert(null, null);
        assertNull(result);
    }


    @Test
    public void testConvertEmpty()
    {
        Float result = underTest.convert("", null);
        assertNull(result);
    }


    @Test
    public void testConvertBlank()
    {
        Float result = underTest.convert("    ", null);
        assertNull(result);
    }


    @Test
    public void testConvertFoo()
    {
        Float result = underTest.convert("foo", null);
        assertNull(result);
    }


    @Test
    public void testConvertPositive()
    {
        Float result = underTest.convert("23", null);
        assertEquals(23.0, result.floatValue(), .1);
        result = underTest.convert("23.123", null);
        assertEquals(23.123, result.floatValue(), .1);
    }


    @Test
    public void testConvertNegative()
    {
        Float result = underTest.convert("-23", null);
        assertEquals(-23.0, result.floatValue(), .1);
        result = underTest.convert("-23.456", null);
        assertEquals(-23.456, result.floatValue(), .1);
    }
}
