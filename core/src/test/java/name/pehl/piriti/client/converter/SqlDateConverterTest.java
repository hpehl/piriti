package name.pehl.piriti.client.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */

public class SqlDateConverterTest
{
    private SqlDateConverter underTest;


    @Before
    public void setUp() throws Exception
    {
        underTest = new SqlDateConverter();
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
    public void testConvertDate()
    {
        String value = "1973-09-02";
        Date result = underTest.convert(value, null);
        assertEquals(Date.valueOf(value), result);
    }
}
