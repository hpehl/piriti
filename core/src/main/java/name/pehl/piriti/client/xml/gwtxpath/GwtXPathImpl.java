package name.pehl.piriti.client.xml.gwtxpath;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.xml.client.Attr;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.Text;
import com.mouchel.gwt.xpath.client.XPath;

/**
 * {@link name.pehl.piriti.client.xml.XPath} implementation using <a
 * href="http://code.google.com/p/gwtxpath/">gwtxpath</a>.
 * <p>
 * Does <b>not</b> support namespaces!
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class GwtXPathImpl implements name.pehl.piriti.client.xml.XPath
{
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Element> getElements(Document document)
    {
        List<Element> elements = null;
        if (document != null)
        {
            elements = getElements(document.getDocumentElement());
        }
        return elements;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Element> getElements(Element element)
    {
        List<Element> elements = null;
        if (element != null)
        {
            NodeList childNodes = element.getChildNodes();
            int length = childNodes.getLength();
            if (length > 0)
            {
                elements = new ArrayList<Element>();
                for (int i = 0; i < length; i++)
                {
                    Node item = childNodes.item(i);
                    if (item instanceof Element)
                    {
                        elements.add((Element) item);
                    }
                }
            }
        }
        return elements;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Element> getElements(Document document, String xpath)
    {
        return XPath.evaluate(document, xpath, Element.class);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Element> getElements(Element element, String xpath)
    {
        return XPath.evaluate(element, xpath, Element.class);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Element getElement(Document document, String xpath)
    {
        return XPath.evaluateSingle(document, xpath, Element.class);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Element getElement(Element element, String xpath)
    {
        return XPath.evaluateSingle(element, xpath, Element.class);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getValues(Document document, String xpath)
    {
        List<Node> nodes = XPath.evaluate(document, xpath);
        return internalGetValues(nodes, xpath);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getValues(Element element, String xpath)
    {
        List<Node> nodes = XPath.evaluate(element, xpath);
        return internalGetValues(nodes, xpath);
    }


    private String[] internalGetValues(List<Node> nodes, String xpath)
    {
        List<String> values = null;
        if (nodes != null && !nodes.isEmpty())
        {
            values = new ArrayList<String>();
            for (Node node : nodes)
            {
                String value = null;
                if (node instanceof Attr)
                {
                    value = ((Attr) node).getValue();
                }
                else if (node instanceof Text)
                {
                    value = ((Text) node).getData();
                }
                values.add(value);
            }
        }
        if (values != null)
        {
            return values.toArray(new String[0]);
        }
        return null;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue(Document document, String xpath)
    {
        Node node = XPath.evaluateSingle(document, xpath);
        return internalGetValue(node, xpath);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue(Element element, String xpath)
    {
        Node node = XPath.evaluateSingle(element, xpath);
        return internalGetValue(node, xpath);
    }


    private String internalGetValue(Node node, String xpath)
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
