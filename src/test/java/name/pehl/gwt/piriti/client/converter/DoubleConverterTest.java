package name.pehl.gwt.piriti.client.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.DecimalFormat;
import java.text.ParseException;

import name.pehl.gwt.piriti.client.converter.DoubleConverter;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $Author:$
 * @version $Revision:$
 */
public class DoubleConverterTest extends DoubleConverter
{
    private DoubleConverter underTest;


    @Override
    protected Double parseDouble(String value, String format)
    {
        DecimalFormat parser = new DecimalFormat(format);
        try
        {
            Double result = (Double) parser.parse(value);
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
        underTest = new DoubleConverterTest();
    }


    @Test
    public void testConvertNull()
    {
        Double result = underTest.convert(null, null);
        assertNull(result);
    }


    @Test
    public void testConvertEmpty()
    {
        Double result = underTest.convert("", null);
        assertNull(result);
    }


    @Test
    public void testConvertBlank()
    {
        Double result = underTest.convert("    ", null);
        assertNull(result);
    }


    @Test
    public void testConvertFoo()
    {
        Double result = underTest.convert("foo", null);
        assertNull(result);
    }


    @Test
    public void testConvertPositive()
    {
        Double result = underTest.convert("23", null);
        assertEquals(23.0, result.doubleValue(), .0);
        result = underTest.convert("23.123", null);
        assertEquals(23.123, result.doubleValue(), .0);
    }


    @Test
    public void testConvertNegative()
    {
        Double result = underTest.convert("-23", null);
        assertEquals(-23.0, result.doubleValue(), .0);
        result = underTest.convert("-23.456", null);
        assertEquals(-23.456, result.doubleValue(), .0);
    }
}
