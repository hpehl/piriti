package name.pehl.piriti.xml.client;

/**
 * Helper class for serializing XML.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class XmlBuilder
{
    private final StringBuilder out;
    private String currentElement;


    public XmlBuilder()
    {
        out = new StringBuilder();
    }


    public XmlBuilder prolog(String version, String encoding)
    {
        out.append("<?xml version=\"").append(version).append("\" encoding=\"").append(encoding).append("\"?>\n");
        return this;
    }


    public XmlBuilder start(String element)
    {
        this.currentElement = element;
        out.append("<").append(currentElement).append(">");
        return this;
    }


    public XmlBuilder append(String value)
    {
        out.append(value);
        return this;
    }


    public XmlBuilder end()
    {
        out.append("</").append(currentElement).append(">");
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
