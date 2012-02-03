package name.pehl.piriti.xml.client;

import java.util.List;

import name.pehl.piriti.commons.client.HasModelReadHandler;
import name.pehl.piriti.commons.client.Reader;
import name.pehl.totoe.xml.client.Document;
import name.pehl.totoe.xml.client.Element;
import name.pehl.totoe.xml.client.XmlParseException;

/**
 * Interface for converting XML document / element to an instance of T or a list
 * of Ts. All properties of T and superclasses of T are handled by the generated
 * XmlReader implementation. If there's data for the property it is assigned
 * with the converted value, otherwise the property remains unchanged.
 * <p>
 * The setup of the XmlReader is inspired by the UiBinder and is typically
 * specified as an inner class:
 * 
 * <pre>
 * pubilc SmartYakizakanaState
 * {
 *     interface Reader extends XmlReader&lt;SmartYakizakanaState&gt; {}
 *     public static final Reader XML = GWT.create(Reader.class);
 *     
 *     // The properties of this POJO annotated with XmlField.
 * }
 * </pre>
 * 
 * An XML document can then be converted to an instance of SmartYakizakanaState
 * by calling
 * 
 * <pre>
 * Document document = ...;
 * SmartYakizakanaState sys = SmartYakizakanaState.XML.read(document);
 * </pre>
 * 
 * @param <T>
 *            The type
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 46 $
 */
public interface XmlReader<T> extends Reader<T>, HasModelReadHandler<T, Element>
{
    // -------------------------------------------------------------- read list

    /**
     * Convert the direct children of the root element from the specified XML to
     * a list of Ts according to the annotated properties in T.
     * <p>
     * Please note: Use this method only if your XML does <i>not</i> contain
     * namespaces. Othherwise you have to parse the xml yourself and call
     * {@link #readList(Document)}:
     * 
     * <pre>
     * String xmlWithNamespaces = &quot;...&quot;;
     * Document document = new XmlParser().parse(xmlWithNamespaces, &quot;...&quot;);
     * List&lt;T&gt; models = reader.readList(document);
     * </pre>
     * 
     * @param xml
     *            The XML used as input. May be <code>null</code>.
     * @return A list of T instances with the mapped XML data or
     *         <code>null</code> if the {@code xml} is {@code null} or empty.
     * @throws XmlParseException
     *             if there's an error parsing the xml
     */
    List<T> readList(String xml) throws XmlParseException;


    /**
     * Convert the direct children of the documents root element to a list of Ts
     * according to the annotated properties in T.
     * 
     * @param document
     *            The XML document used as input. May be <code>null</code>.
     * @return A list of T instances with the mapped XML data or
     *         <code>null</code> if the {@code document} is {@code null} or the
     *         document has no root element.
     */
    List<T> readList(Document document);


    /**
     * Convert the data in the specified document to a list of Ts according to
     * the annotated properties in T.
     * 
     * @param document
     *            The XML document used as input. May be <code>null</code>.
     * @param xpath
     *            An xpath expression which should result in a list of elements
     *            which in turn is used to generate the instances of T
     * @return A list of T instances with the mapped XML data or
     *         <code>null</code> if the {@code document} is {@code null}.
     */
    List<T> readList(Document document, String xpath);


    /**
     * Convert the direct children of the specified element to a list of Ts
     * according to the annotated properties in T.
     * 
     * @param document
     *            The XML element used as input. May be <code>null</code>.
     * @return A list of T instances with the mapped XML data or null if the
     *         {@code element} is {@code null}.
     */
    List<T> readList(Element element);


    /**
     * Convert the data in the specified element to a list of Ts according to
     * the annotated properties in T.
     * 
     * @param document
     *            The XML element used as input. May be <code>null</code>.
     * @param xpath
     *            An xpath expression which should result in a list of elements
     *            which in turn are used to generate the instances of T
     * @return A list of T instances with the mapped XML data or null if the
     *         {@code element} is {@code null}.
     */
    List<T> readList(Element element, String xpath);


    // ------------------------------------------------------------ read single

    /**
     * Convert the data in the specified document to an instance of T according
     * to the annotated properties in T.
     * <p>
     * Please note: Use this method only if your XML does <i>not</i> contain
     * namespaces. Othherwise you have to parse the xml yourself and call
     * {@link #read(Document)}:
     * 
     * <pre>
     * String xmlWithNamespaces = &quot;...&quot;;
     * Document document = new XmlParser().parse(xmlWithNamespaces, &quot;...&quot;);
     * T model = reader.read(document);
     * </pre>
     * 
     * @param xml
     *            The XML used as input. May be <code>null</code>.
     * @return An instance of T with the mapped XML data or {@code null} if the
     *         {@code xml} is {@code null} or empty.
     * @throws XmlParseException
     *             if there's an error parsing the xml
     */
    T read(String xml) throws XmlParseException;


    /**
     * Convert the data in the specified document to an instance of T according
     * to the annotated properties in T.
     * 
     * @param document
     *            The XML document used as input. May be <code>null</code>.
     * @return An instance of T with the mapped XML data or {@code null} if the
     *         {@code document} is {@code null} or the document has no root
     *         element.
     */
    T read(Document document);


    /**
     * Convert the data in the specified element to an instance of T according
     * to the annotated properties in T.
     * 
     * @param element
     *            The XML element used as input. May be <code>null</code>.
     * @return An instance of T with the mapped XML data or {@code null} if the
     *         {@code element} is {@code null}.
     */
    T read(Element element);
}
