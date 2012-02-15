package name.pehl.piriti.converter.client;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 19 $
 */
public class BooleanConverterTest
{
    private BooleanConverter underTest;


    @Before
    public void setUp() throws Exception
    {
        underTest = new BooleanConverter();
    }


    @Test
    public void testConvertNull()
    {
        Boolean result = underTest.convert(null);
        assertNull(result);
    }


    @Test
    public void testConvertEmpty()
    {
        Boolean result = underTest.convert("");
        assertNull(result);
    }


    @Test
    public void testConvertBlank()
    {
        Boolean result = underTest.convert("    ");
        assertNull(result);
    }


    @Test
    public void testConvertFoo()
    {
        Boolean result = underTest.convert("foo");
        assertFalse(result);
    }


    @Test
    public void testConvertFalse()
    {
        Boolean result = underTest.convert("false");
        assertFalse(result);
    }


    @Test
    public void testConvertTrue()
    {
        Boolean result = underTest.convert("true");
        assertTrue(result);
    }
}
