package name.pehl.piriti.client.gwttest.specialjsonkey;

import name.pehl.piriti.client.gwttest.AbstractPlaygroundTest;

/**
 * @author $Author$
 * @version $Revision$
 */
public class RequestTest extends AbstractPlaygroundTest
{
    public void testReadWriteRequest()
    {
        String jsonIn = RequestResources.INSTANCE.requestJson().getText();
        Request request = Request.READER.read(jsonIn);
        assertEquals("1", request.requestId);

        String jsonOut = Request.WRITER.toJson(request);
        assertEquals(jsonIn, jsonOut);
    }
}
