package name.pehl.piriti.client.innerclasses;

import name.pehl.piriti.client.AbstractPlaygroundTest;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 1305 $
 */
public class JsonIssue49Test extends AbstractPlaygroundTest
{
    public void testWriteIssue49()
    {
        Issue49 issue49 = new Issue49();
        String json = Issue49.WRITER.toJson(issue49);
        assertNotNull(json);
    }
}
