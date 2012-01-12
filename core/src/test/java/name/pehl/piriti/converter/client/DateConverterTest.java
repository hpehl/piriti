package name.pehl.piriti.converter.client;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Test;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 19 $
 */
public class DateConverterTest
{
    private TestableDateConverter underTest;


    @Test
    public void testConvertNull()
    {
        underTest = new TestableDateConverter();
        Date result = underTest.convert(null);
        assertNull(result);
    }


    @Test
    public void testConvertEmpty()
    {
        underTest = new TestableDateConverter();
        Date result = underTest.convert("");
        assertNull(result);
    }


    @Test
    public void testConvertBlank()
    {
        underTest = new TestableDateConverter();
        Date result = underTest.convert("    ");
        assertNull(result);
    }


    @Test
    public void testConvertFoo()
    {
        underTest = new TestableDateConverter();
        Date result = underTest.convert("foo");
        assertNull(result);
    }


    @Test
    public void testConvertDefaultFormat()
    {
        underTest = new TestableDateConverter();
        Date now = new Date();
        TestableDateTimeFormat dateTimeFormat = new TestableDateTimeFormat();
        String nowAsString = dateTimeFormat.format(now);
        Date result = underTest.convert(nowAsString);
        assertEquals(now, result);
    }


    @Test
    public void testConvertCustomFormat()
    {
        String format = "dd MMM yy HH/mm/ss/SSS";
        underTest = new TestableDateConverter(format);
        Date now = new Date();
        TestableDateTimeFormat dateTimeFormat = new TestableDateTimeFormat(format);
        String nowAsString = dateTimeFormat.format(now);
        Date result = underTest.convert(nowAsString);
        assertEquals(now, result);
    }
}
