package name.pehl.piriti.client.xml;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

/**
 * Contains common methods which don't require XPath functionality.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */

public abstract class AbstractXPathImpl implements XPath
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
}
