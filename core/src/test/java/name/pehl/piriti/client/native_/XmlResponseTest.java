package name.pehl.piriti.client.native_;

import name.pehl.totoe.xml.client.NodeType;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 131 $
 */
public class XmlResponseTest extends AbstractResponseTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String xml = ResponseResources.INSTANCE.responseXml().getText();
        Response response = Response.XML_READER.read(xml);
        assertResponse(response);
        assertTrue(response.resultAsString.matches("<result>\\s*<foo>bar</foo>\\s*</result>"));
        assertEquals(NodeType.ELEMENT, response.resultAsNode.getType());
        assertEquals(1, response.resultAsElement.getChildren(NodeType.ELEMENT).size());
        assertEquals("bar", response.resultAsElement.selectValue("foo"));
        assertNull(response.resultAsJsonValue);
        assertNull(response.resultAsJsonObject);
    }

    // ------------------------------------------------------------ write tests

    // NYI
}
