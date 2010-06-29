package name.pehl.piriti.client.gwttest.xml;

import static name.pehl.piriti.client.gwttest.xml.XmlParserResources.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import name.pehl.piriti.client.gwttest.AbstractPiritiTest;
import name.pehl.piriti.client.xml.Node;
import name.pehl.piriti.client.xml.NodeType;
import name.pehl.piriti.client.xml.XmlGinjector;
import name.pehl.piriti.client.xml.XmlParser;
import name.pehl.piriti.client.xml.XmlReader;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class XmlParserTest extends AbstractPiritiTest
{
    static final Map<String, String> NAMESPACES = new HashMap<String, String>();
    static
    {
        NAMESPACES.put(XmlReader.DNS, "http://code.google.com/p/piriti");
        NAMESPACES.put("foo", "http://code.google.com/p/piriti/foo");
        NAMESPACES.put("bar", "http://code.google.com/p/piriti/foo");
    }


    public void testParse()
    {
        Node document = parse();

        // Document node
        assertEquals(DOCUMENT_NAME, document.getNodeName());
        assertEquals(NodeType.DOCUMENT, document.getNodeType());
        assertNull(document.getNodeValue());
        List<Node> children = document.getChildNodes();
        assertEquals(1, children.size());

        // Root node
        Node root = children.get(0);
        assertEquals(ROOT_NAME, root.getNodeName());
        assertEquals(NodeType.ELEMENT, root.getNodeType());
        assertNull(root.getNodeValue());
        assertEquals(ASIN, root.getAttribute("id"));
    }


    public void testSelect()
    {

    }


    private Node parse()
    {
        String xml = XmlParserResources.INSTANCE.swissArmyKnife().getText();
        XmlParser xmlParser = XmlGinjector.INJECTOR.getXmlParser();
        Node document = xmlParser.parse(xml, NAMESPACES);
        return document;
    }
}
