package name.pehl.gwt.piriti.client.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import name.pehl.gwt.piriti.client.converter.CharacterConverter;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $LastChangedBy:$ 
 * @version $LastChangedRevision:$ 
 */
public class CharacterConverterTest
{
    private CharacterConverter underTest;


    @Before
    public void setUp() throws Exception
    {
        underTest = new CharacterConverter();
    }


    @Test
    public void testConvertNull()
    {
        Character result = underTest.convert(null, null);
        assertNull(result);
    }


    @Test
    public void testConvertEmpty()
    {
        Character result = underTest.convert("", null);
        assertNull(result);
    }


    @Test
    public void testConvertBlank()
    {
        Character result = underTest.convert("    ", null);
        assertEquals(' ', result.charValue());
    }


    @Test
    public void testConvertFoo()
    {
        Character result = underTest.convert("foo", null);
        assertEquals('f', result.charValue());
    }


    @Test
    public void testConvertValidCharacter()
    {
        Character result = underTest.convert("c", null);
        assertEquals('c', result.charValue());
    }
}
