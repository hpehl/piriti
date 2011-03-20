package name.pehl.piriti.property.client;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class NoopPropertyGetterTest
{
    private NoopPropertyGetter underTest;


    @Before
    public void setUp() throws Exception
    {
        underTest = new NoopPropertyGetter();
    }


    @Test(expected = UnsupportedOperationException.class)
    public void getNull()
    {
        underTest.get(null);
    }


    @Test(expected = UnsupportedOperationException.class)
    public void getNotNull()
    {
        underTest.get("nope");
    }
}
