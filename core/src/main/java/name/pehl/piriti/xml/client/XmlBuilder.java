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
        if (isNotEmpty(path))
        {
            this.root = fromPath(path);
            this.currentElement = this.root.eldestDescendant();
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
            if (isNotEmpty(path))
            {
                Element first = fromPath(path);
                if (first != null)
                {
                    Element last = first.eldestDescendant();
                    last.children.add(xmlBuilder.root);
                    currentElement.children.add(first);
                }
            }
        }
        return this;
    }


    public XmlBuilder append(String path)
    {
        return append(path, (String) null);
    }


    public XmlBuilder append(String path, String value)
    {
        if (currentElement != null)
        {
            if (isNotEmpty(path))
            {
                if (path.contains("@"))
                {
                    // attribute
                    if (path.contains("/"))
                    {
                        // an xpath in the following form: a/b/c/.../x/y/@z
                        String[] parts = path.split("/@");
                        if (parts.length == 2)
                        {
                            Element first = fromPath(parts[0]);
                            if (first != null)
                            {
                                Element last = first.eldestDescendant();
                                if (isNotEmpty(parts[1]) && isNotEmpty(value))
                                {
                                    last.attributes.put(parts[1], value);
                                    currentElement.children.add(first);
                                }
                            }
                        }
                    }
                    else
                    {
                        // the easy part: just one attribute
                        if (isAttribute(path) && isNotEmpty(value))
                        {
                            currentElement.attributes.put(path.substring(1), value);
                        }
                    }
                }
                else
                {
                    // (nested) element(s)
                    Element first = fromPath(path);
                    if (first != null)
                    {
                        Element last = first.eldestDescendant();
                        if (isNotEmpty(value))
                        {
                            last.content = value;
                        }
                        currentElement.children.add(first);
                    }
                }
            }
        }
        return this;
    }


    private boolean isNotEmpty(String string)
    {
        return string != null && string.trim().length() != 0;
    }


    private boolean isAttribute(String path)
    {
        return isNotEmpty(path) && path.startsWith("@") && path.length() > 1;
    }


    /**
     * Converts XPaths expressions in the form of {@code a/b/c/.../x/y/z} to an
     * element with children, grand children, ...
     * 
     * @param path
     *            An XPath expression in the form of {@code a/b/c/.../x/y/z}
     * @return the eldest descendant element of the XPath expression with
     *         children, grand children, ...
     */
    private Element fromPath(String path)
    {
        Element result = null;
        String[] parts = filterEmpty(path.split("/"));
        if (parts.length == 1)
        {
            // the easy part: just one attribute / element
            result = new Element(parts[0]);
        }
        else if (parts.length > 1)
        {
            // an xpath in the following form a/b/c/.../x/y/z
            Element current = new Element(parts[0]);
            result = current;
            for (int i = 1; i < parts.length; i++)
            {
                if (isNotEmpty(path))
                {
                    Element child = new Element(parts[i]);
                    current.children.add(child);
                    current = child;
                }
            }
        }
        return result;
    }


    private String[] filterEmpty(String[] strings)
    {
        List<String> result = new ArrayList<String>();
        if (strings != null && strings.length != 0)
        {
            for (String string : strings)
            {
                if (isNotEmpty(string))
                {
                    result.add(string);
                }
            }
        }
        return result.toArray(new String[] {});
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


        Element eldestDescendant()
        {
            Element result = this;
            if (!children.isEmpty())
            {
                result = children.get(0).eldestDescendant();
            }
            return result;
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
