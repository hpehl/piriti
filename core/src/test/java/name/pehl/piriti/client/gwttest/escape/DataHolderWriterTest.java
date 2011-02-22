package name.pehl.piriti.client.gwttest.escape;

import name.pehl.piriti.client.gwttest.AbstractPiritiTest;

/**
 * JsonWriter tests (special characters, escaping, ...)
 * 
 * @author $Author: harald.pehl $
 * @version $Revision: 1095 $
 */
public class DataHolderWriterTest extends AbstractPiritiTest
{
    // -------------------------------------------------------------- test data

    public void testEscaping()
    {
        String quotedString = "A \"quoted\" string with \u00fbnicode and slash: \"\\\"";
        DataHolder dh = new DataHolder();
        dh.data = quotedString;
        dh.converterData = quotedString;
        String json = DataHolder.JSON_WRITER.toJson(dh);
        assertNotNull(json);
    }
}
