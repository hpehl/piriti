package name.pehl.piriti.client.xml;

import java.util.List;

import com.google.gwt.xml.client.Attr;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.Text;
import com.mouchel.gwt.xpath.client.XPath;

/**
 * Utility methods for xpath handling.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 8 $
 */
public final class XPathUtils
{
    private XPathUtils()
    {
    }


    /**
     * Returns a list of elements for the specifed xpath.
     * 
     * @param document
     * @param xpath
     * @return The list of elements matching the xpath or an empty list if no
     *         matching elements were found
     */
    public static List<Element> getElements(Document document, String xpath)
    {
        return XPath.evaluate(document, xpath, Element.class);
    }


    /**
     * Returns a list of elements for the specifed xpath.
     * 
     * @param element
     * @param xpath
     * @return The list of elements matching the xpath or an empty list if no
     *         matching elements were found
     */
    public static List<Element> getElements(Element element, String xpath)
    {
        return XPath.evaluate(element, xpath, Element.class);
    }


    /**
     * Returns the element for the specifed xpath.
     * 
     * @param document
     * @param xpath
     * @return The element matching the xpath or {@code null} if no matching
     *         element was found
     */
    public static Element getElement(Document document, String xpath)
    {
        return XPath.evaluateSingle(document, xpath, Element.class);
    }


    /**
     * Returns the element for the specifed xpath.
     * 
     * @param xpath
     * @return The element matching the xpath or {@code null} if no matching
     *         element was found
     */
    public static Element getElement(Element element, String xpath)
    {
        return XPath.evaluateSingle(element, xpath, Element.class);
    }


    /**
     * Returns the string value for the specified xpath. The node selected by
     * the xpath expression must be an attribute or a text node.
     * 
     * @param document
     * @param xpath
     * @return The string value matched by the xpath or {@code null} if the
     *         xpath does not resolve to an attribute or text node
     */
    public static String getValue(Document document, String xpath)
    {
        Node node = XPath.evaluateSingle(document, xpath);
        return internalGetValue(node, xpath);
    }


    /**
     * Returns the string value for the specified xpath. The node selected by
     * the xpath expression must be an attribute or a text node.
     * 
     * @param element
     * @param xpath
     * @return The string value matched by the xpath or {@code null} if the
     *         xpath does not resolve to an attribute or text node
     */
    public static String getValue(Element element, String xpath)
    {
        Node node = XPath.evaluateSingle(element, xpath);
        return internalGetValue(node, xpath);
    }


    private static String internalGetValue(Node node, String xpath)
    {
        String value = null;
        if (node != null)
        {
            if (node instanceof Attr)
            {
                value = ((Attr) node).getValue();
            }
            else if (node instanceof Text)
            {
                value = ((Text) node).getData();
            }
        }
        return value;
    }
}
