package name.pehl.piriti.client.xml;

import java.util.Map;

/**
 * @author $Author$
 * @version $Date$ $Revision: 623
 *          $
 */
public interface XmlParser
{
    /**
     * Parses the given xml to an instance of {@link Node}.
     * 
     * @param xml
     * @return
     */
    Node parse(String xml);


    /**
     * Parses the given xml to an instance of {@link Node} using the specified
     * namespaces. The namespaces have to be specified as a whilespace-seperated
     * list of namespace declarations as those would appear in an XML document.
     * <p>
     * If your xml has a default namespace and you want to reference it later on
     * in xpath expressions you have to provide a prefix for that default
     * namespace. In case you have the following xml:
     * 
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;
     * &lt;lotteryTicket xmlns="http://code.google.com/p/piriti" 
     *     xmlns:foo="http://code.google.com/p/piriti/foo"
     *     xmlns:bar="http://code.google.com/p/piriti/bar"&gt;
     *     ...
     * &lt;/lotteryTicket&gt;
     * </pre>
     * 
     * Use this code to parse it:
     * 
     * <pre>
     * String xml = ...;
     * String namespaces = &quot;xmlns:default=\&quot;http://code.google.com/p/piriti\&quot;&quot;
     *     + &quot;xmlns:foo=\&quot;http://code.google.com/p/piriti/foo\&quot;&quot;
     *     + &quot;xmlns:bar=\&quot;http://code.google.com/p/piriti/bar\&quot;&quot;;
     * XmlParser xmlParser = XmlGinjector.INJECTOR.getXmlParser();
     * Node node = xmlParser.parse(xml, namespaces);
     * </pre>
     * 
     * @param xml
     * @param namespaces
     *            a whilespace-seperated list of namespace declarations as those
     *            would appear in an XML document.
     * @return
     */
    Node parse(String xml, String namespaces);


    /**
     * Parses the given xml to an instance of {@link Node} using the specified
     * namespaces. The namespaces have to be specified as a map with the
     * namespace prefix as key and the namespace uri as value.
     * <p>
     * If your xml has a default namespace and you want to reference it later on
     * in xpath expressions you have to provide a prefix for that default
     * namespace. In case you have the following xml:
     * 
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;
     * &lt;lotteryTicket xmlns="http://code.google.com/p/piriti" 
     *     xmlns:foo="http://code.google.com/p/piriti/foo"
     *     xmlns:bar="http://code.google.com/p/piriti/bar"&gt;
     *     ...
     * &lt;/lotteryTicket&gt;
     * </pre>
     * 
     * Use this code to parse it:
     * 
     * <pre>
     * String xml = ...;
     * Map&lt;String, String&gt; namespaces = new HashMap&lt;String, String&gt;();
     * namespaces.put(&quot;default&quot;, &quot;http://code.google.com/p/piriti&quot;); // default namespace
     * namespaces.put(&quot;foo&quot;, &quot;http://code.google.com/p/piriti/foo&quot;);
     * namespaces.put(&quot;bar&quot;, &quot;http://code.google.com/p/piriti/bar&quot;);
     * XmlParser xmlParser = XmlGinjector.INJECTOR.getXmlParser();
     * Node node = xmlParser.parse(xml, namespaces);
     * </pre>
     * 
     * @param xml
     * @param namespaces
     *            a map with the namespace prefix as key and the namespace uri
     *            as value.
     * @return
     */
    Node parse(String xml, Map<String, String> namespaces);
}
