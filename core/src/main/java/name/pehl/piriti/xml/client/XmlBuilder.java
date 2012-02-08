package name.pehl.piriti.xml.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Helper class for serializing XML.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class XmlBuilder
{
    private final Element root;
    private final Element currentElement;


    public XmlBuilder(String path)
    {
        if (path != null && path.trim().length() != 0)
        {
            String[] parts = path.split("/");
            if (parts.length == 1)
            {
                // the easy part: just one attribute / element
                this.root = new Element(parts[0]);
                this.currentElement = root;
            }
            else if (parts.length > 1)
            {
                // an xpath in the following form a/b/c/.../x/y/z
                Element element = new Element(parts[0]);
                this.root = element;
                for (int i = 1; i < parts.length; i++)
                {
                    Element child = new Element(parts[i]);
                    element.children.add(child);
                    element = child;
                }
                this.currentElement = element;
            }
            else
            {
                this.root = null;
                this.currentElement = null;
            }
        }
        else
        {
            this.root = null;
            this.currentElement = null;
        }
    }


    public XmlBuilder append(XmlBuilder xmlBuilder)
    {
        if (currentElement != null)
        {
            currentElement.children.add(xmlBuilder.root);
        }
        return this;
    }


    public XmlBuilder append(String path, XmlBuilder xmlBuilder)
    {
        if (currentElement != null)
        {
            currentElement.children.add(xmlBuilder.root);
        }
        return this;
    }


    public XmlBuilder append(String path)
    {
        return this;
    }


    public XmlBuilder append(String path, String value)
    {
        if (currentElement != null)
        {
            if (path != null && path.length() != 0 && value != null && value.length() != 0)
            {
                String[] parts = path.split("/");
                if (parts.length == 1)
                {
                    // the easy part: just one attribute / element
                    if (isAttribute(parts[0]))
                    {
                        // attribute
                        currentElement.attributes.put(parts[0].substring(1), value);
                    }
                    else
                    {
                        // element
                        Element element = new Element(parts[0]);
                        element.content = value;
                        currentElement.children.add(element);
                    }
                }
                else if (parts.length > 1)
                {
                    // an xpath in one of the following forms
                    // a/b/c/.../x/y/@z
                    // a/b/c/.../x/y/z
                    Element element = new Element(parts[0]);
                    currentElement.children.add(element);
                    for (int i = 1; i < parts.length - 1; i++)
                    {
                        Element child = new Element(parts[i]);
                        element.children.add(child);
                        element = child;
                    }
                    if (isAttribute(parts[parts.length - 1]))
                    {
                        // a/b/c/.../x/y/@z
                        element.attributes.put(parts[parts.length - 1].substring(1), value);
                    }
                    else
                    {
                        // a/b/c/.../x/y/z
                        Element last = new Element(parts[parts.length - 1]);
                        last.content = value;
                        element.children.add(last);
                    }
                }
            }
        }
        return this;
    }


    private boolean isAttribute(String path)
    {
        return path.startsWith("@") && path.length() > 1;
    }


    /**
     * @return The current markup
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        if (root != null)
        {
            toString(root, builder);
        }
        return builder.toString();
    }


    private void toString(Element element, StringBuilder builder)
    {
        builder.append("<").append(element.name);
        if (!element.attributes.isEmpty())
        {
            for (Entry<String, String> entry : element.attributes.entrySet())
            {
                builder.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
            }
        }
        if (!element.children.isEmpty())
        {
            builder.append(">");
            for (Element child : element.children)
            {
                toString(child, builder);
            }
            builder.append("</").append(element.name).append(">");
        }
        else if (element.content != null)
        {
            builder.append(">").append(element.content).append("</").append(element.name).append(">");
        }
        else
        {
            builder.append("/>");
        }
    }

    public class Element
    {
        final String name;
        Map<String, String> attributes;
        List<Element> children;
        String content;


        Element(String name)
        {
            this.name = name;
            this.attributes = new HashMap<String, String>();
            this.children = new ArrayList<XmlBuilder.Element>();
        }


        @Override
        public String toString()
        {
            StringBuilder builder = new StringBuilder("Element[").append(name);
            if (!attributes.isEmpty())
            {
                builder.append(", attributes: ").append(attributes);
            }
            builder.append("]");
            return builder.toString();
        }
    }
}
