package name.pehl.gwt.piriti.client.xml;

import java.util.List;

import com.google.gwt.xml.client.Attr;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.Text;
import com.mouchel.gwt.xpath.client.XPath;

/**
 * Some utility methods for xpath handling.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public final class XPathUtils
{
    private XPathUtils()
    {
    }


    public static List<Element> getElements(Document document, String xpath)
    {
        return XPath.evaluate(document, xpath, Element.class);
    }


    public static List<Element> getElements(Element element, String xpath)
    {
        return XPath.evaluate(element, xpath, Element.class);
    }


    public static Element getElement(Document document, String xpath)
    {
        return XPath.evaluateSingle(document, xpath, Element.class);
    }


    public static Element getElement(Element element, String xpath)
    {
        return XPath.evaluateSingle(element, xpath, Element.class);
    }


    public static String getValue(Document document, String xpath)
    {
        Node node = XPath.evaluateSingle(document, xpath);
        return internalGetValue(node, xpath);
    }


    public static String getValue(Element element, String xpath)
    {
        Node node = XPath.evaluateSingle(element, xpath);
        return internalGetValue(node, xpath);
    }


    public static String internalGetValue(Node node, String xpath)
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
