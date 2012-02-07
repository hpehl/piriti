package name.pehl.piriti.xml.client;

import java.util.Stack;

/**
 * Helper class for serializing XML.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class XmlBuilder
{
    private final StringBuilder out;
    private Stack<String> elements;


    public XmlBuilder()
    {
        this.out = new StringBuilder();
        this.elements = new Stack<String>();
    }


    public XmlBuilder prolog(String version, String encoding)
    {
        out.append("<?xml version=\"").append(version).append("\" encoding=\"").append(encoding).append("\"?>\n");
        return this;
    }


    public XmlBuilder start(String element)
    {
        elements.push(element);
        out.append("<").append(element).append(">");
        return this;
    }


    public XmlBuilder emptyElement(String element)
    {
        out.append("<").append(element).append("/>");
        return this;
    }


    public XmlBuilder append(String value)
    {
        // TODO: Implement XML escaping for <, >, &
        out.append(value);
        return this;
    }


    public XmlBuilder end()
    {
        String element = elements.pop();
        out.append("</").append(element).append(">");
        return this;
    }


    /**
     * @return The current markup
     */
    @Override
    public String toString()
    {
        return out.toString();
    }
}
