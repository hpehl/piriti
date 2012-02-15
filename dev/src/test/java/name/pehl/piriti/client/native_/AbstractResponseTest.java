package name.pehl.piriti.client.native_;

import name.pehl.piriti.client.AbstractPiritiTest;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2011-03-20 21:02:59 +0100 (So, 20 Mrz 2011) $ $Revision: 295
 *          $
 */
public abstract class AbstractResponseTest extends AbstractPiritiTest
{
    protected void assertResponse(Response response)
    {
        assertNotNull(response);
        assertEquals("1.0", response.version);
        assertEquals(200, response.recode);
    }
}
