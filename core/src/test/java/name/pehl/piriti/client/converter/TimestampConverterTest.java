package name.pehl.piriti.client.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */

public class TimestampConverterTest
{
    private TimestampConverter underTest;


    @Before
    public void setUp() throws Exception
    {
        underTest = new TimestampConverter();
    }


    @Test
    public void testConvertNull()
    {
        Timestamp result = underTest.convert(null, null);
        assertNull(result);
    }


    @Test
    public void testConvertEmpty()
    {
        Timestamp result = underTest.convert("", null);
        assertNull(result);
    }


    @Test
    public void testConvertBlank()
    {
        Timestamp result = underTest.convert("    ", null);
        assertNull(result);
    }


    @Test
    public void testConvertFoo()
    {
        Timestamp result = underTest.convert("foo", null);
        assertNull(result);
    }


    @Test
    public void testConvertDate()
    {
        String value = "1973-09-02 11:22:33";
        Timestamp result = underTest.convert(value, null);
        assertEquals(Timestamp.valueOf(value), result);
    }
}
