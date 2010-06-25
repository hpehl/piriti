package name.pehl.piriti.client.xml.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import name.pehl.piriti.client.xml.Node;
import name.pehl.piriti.client.xml.XmlParser;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class SarissaXmlParserImpl implements XmlParser
{
    @SuppressWarnings("unused")
    private static final JavaScriptObject xmlParser = SarissaXmlParserImpl.initialize();


    // ---------------------------------------------------------- parse methods

    public Node parse(String xml)
    {
        return parse(xml, null);
    }


    @Override
    public Node parse(String xml, Map<String, String> namespaces)
    {
        if (xml == null || xml.length() == 0)
        {
            return null;
        }
        return parseImpl(xml, getNamespaces(namespaces));
    }


    // --------------------------------------------------------- helper methods

    /**
     * Converts the specifed namespaces in the map to a whilespace-seperated
     * list of namespace declarations as those would appear in an XML document.
     * 
     * @param namespaces
     *            Namespaces with the namespace prefix as key and the namespace
     *            uri as value.
     * @return a whilespace-seperated list of namespace declarations as those
     *         would appear in an XML document or <code>null</code> if the
     *         namespaces argument was <code>null</code> or empty.
     */
    private String getNamespaces(Map<String, String> namespaces)
    {
        String result = null;
        if (namespaces != null && !namespaces.isEmpty())
        {
            StringBuilder builder = new StringBuilder();
            for (Iterator<Entry<String, String>> iter = namespaces.entrySet().iterator(); iter.hasNext();)
            {
                Entry<String, String> entry = iter.next();
                String prefix = entry.getKey();
                String uri = entry.getValue();
                if (prefix != null && prefix.length() != 0 && uri != null && uri.length() != 0)
                {
                    builder.append("xmlns:");
                    builder.append(prefix);
                    builder.append("=\"");
                    builder.append(uri);
                    builder.append("\"");
                    if (iter.hasNext())
                    {
                        builder.append(" ");
                    }
                }
            }
            result = builder.toString();
        }
        return result;
    }


    // ----------------------------------------------------------- JSNI methods

    private static native JavaScriptObject initialize() /*-{
        return new $wnd.DOMParser();
    }-*/;


    private native Node parseImpl(String xml, String namespaces) /*-{
        var domDoc = @name.pehl.piriti.client.xml.internal.SarissaXmlParserImpl::xmlParser.parseFromString(xmlString, "text/xml");

        var error = $wnd.Sarissa.getParseErrorText(domDoc);
        if (error != $wnd.Sarissa.PARSED_OK) 
        {
            if (error == $wnd.Sarissa.PARSED_EMPTY) 
            {
                throw (new Error("XML file is empty."));
            }
            else if (error == $wnd.Sarissa.PARSED_UNKNOWN_ERROR) 
            {
                throw (new Error("Unkown error parsing xml file."));
            }
            else 
            {
                throw (new Error(error));
            }
            return null;
        }

        $wnd.Sarissa.setXpathNamespaces(domDoc, namespaces);
        return @name.pehl.piriti.client.xml.internal.SarissaNodeImpl::create(Lcom/google/gwt/core/client/JavaScriptObject;)(domDoc);
    }-*/;
}
