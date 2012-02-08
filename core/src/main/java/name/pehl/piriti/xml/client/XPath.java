package name.pehl.piriti.xml.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import name.pehl.piriti.xml.client.XPath.Element;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class XPath implements Iterable<Element>
{
    private final List<Element> elements;
    private final List<Element> allButLastElement;
    private final List<Element> allButLastElementReversed;


    public XPath(String path)
    {
        elements = new ArrayList<XPath.Element>();
        allButLastElement = new ArrayList<XPath.Element>();
        allButLastElementReversed = new ArrayList<XPath.Element>();
        if (path != null && path.trim().length() != 0)
        {
            String[] parts = path.split("/");
            if (parts.length > 1)
            {
            }

            for (String part : parts)
            {
                Element element = new Element(part);
                elements.add(element);
                allButLastElement.add(element);
                allButLastElementReversed.add(element);
            }
            allButLastElement.remove(allButLastElement.size() - 1);
            allButLastElementReversed.remove(allButLastElementReversed.size() - 1);
            Collections.reverse(allButLastElementReversed);
        }
    }


    @Override
    public Iterator<Element> iterator()
    {
        return elements.iterator();
    }


    public int size()
    {
        return elements.size();
    }


    public boolean isEmpty()
    {
        return elements.isEmpty();
    }


    public Iterator<Element> allButLastElementIterator()
    {
        if (elements.isEmpty())
        {
            return elements.iterator();
        }
        return allButLastElement.iterator();
    }


    public Iterator<Element> allButLastElementReversedIterator()
    {
        if (elements.isEmpty())
        {
            return elements.iterator();
        }
        return allButLastElementReversed.iterator();
    }


    public Element lastElement()
    {
        if (elements.isEmpty())
        {
            return null;
        }
        return elements.get(elements.size() - 1);
    }


    @Override
    public String toString()
    {
        return "Element[" + elements + "]";
    }

    public static class Element
    {
        private final String name;
        private final String attribute;


        Element(String name)
        {
            this(name, null);
        }


        Element(String name, String attribute)
        {
            this.name = name;
            this.attribute = attribute;
        }


        public String getName()
        {
            return name;
        }


        public String getAttribute()
        {
            return attribute;
        }


        public boolean hasAttribute()
        {
            return attribute != null;
        }


        @Override
        public String toString()
        {
            StringBuilder builder = new StringBuilder().append("[").append(name);
            if (hasAttribute())
            {
                builder.append(", @").append(attribute);
            }
            builder.append("]");
            return builder.toString();
        }
    }
}
