package name.pehl.piriti.property.client;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class NoopPropertySetterTest
{
    private NoopPropertySetter underTest;


    @Before
    public void setUp() throws Exception
    {
        underTest = new NoopPropertySetter();
    }


    @Test(expected = UnsupportedOperationException.class)
    public void setNull()
    {
        underTest.set(null, null);
    }


    @Test(expected = UnsupportedOperationException.class)
    public void setNotNull()
    {
        underTest.set("foo", "bar");
    }
}
