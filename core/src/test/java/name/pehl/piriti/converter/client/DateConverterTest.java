package name.pehl.piriti.converter.client;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 19 $
 */
public class DateConverterTest extends DateConverter
{
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZ";
    private DateConverter underTest;


    @Override
    protected Date convertDate(String value, String format)
    {
        SimpleDateFormat dtFormat = new SimpleDateFormat(format == null ? DEFAULT_FORMAT : format);
        try
        {
            return dtFormat.parse(value);
        }
        catch (ParseException e)
        {
            return null;
        }
    }


    @Before
    public void setUp() throws Exception
    {
        underTest = new DateConverterTest();
    }


    @Test
    public void testConvertNull()
    {
        Date result = underTest.convert(null, null);
        assertNull(result);
    }


    @Test
    public void testConvertEmpty()
    {
        Date result = underTest.convert("", null);
        assertNull(result);
    }


    @Test
    public void testConvertBlank()
    {
        Date result = underTest.convert("    ", null);
        assertNull(result);
    }


    @Test
    public void testConvertFoo()
    {
        Date result = underTest.convert("foo", null);
        assertNull(result);
    }


    @Test
    public void testConvertDefaultFormat()
    {
        Date now = new Date();
        SimpleDateFormat dtFormat = new SimpleDateFormat(DEFAULT_FORMAT);
        String nowAsString = dtFormat.format(now);
        Date result = underTest.convert(nowAsString, null);
        assertEquals(now, result);
    }


    @Test
    public void testConvertCustomFormat()
    {
        Date now = new Date();
        String format = "dd MMM yy HH/mm/ss/SSS";
        SimpleDateFormat dtFormat = new SimpleDateFormat(format);
        String nowAsString = dtFormat.format(now);
        Date result = underTest.convert(nowAsString, format);
        assertEquals(now, result);
    }
}
