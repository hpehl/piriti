package name.pehl.gwt.piriti.client.xml;

import java.util.List;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;

/**
 * Interface for converting an XML document / element to an instance of T or a
 * list of Ts. The implementation for this interface is generated for you using
 * deferred binding. All fields of T which are annotated with {@link XmlField}
 * are handled by the generated XmlReader implementation. If the xpath
 * expression behind {@link XmlField} returns some data != null the relevant
 * field is assigned with this data, otherwise the field remains unchanged.
 * <p>
 * Please note that the annotated fields in T must not be private!
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
 *     // The fields of this POJO annotated with XmlField.
 * }
 * </pre>
 * 
 * An XML document can then be converted to an instance of SmartYakizakanaState
 * by calling
 * 
 * <pre>
 * Document document = ...;
 * SmartYakizakanaState sys = SmartYakizakanaState.XML.readSingle(document);
 * </pre>
 * 
 * @param <T>
 *            The type
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public interface XmlReader<T>
{
    /**
     * Convert the data in the specified document to an instance of T according
     * to the annotated fields in T.
     * 
     * @param document
     *            The XML document used as input
     * @return An instance of T with the mapped XML data or {@code null} if the
     *         document was {@code null}.
     */
    T readSingle(Document document);


    /**
     * Convert the data in the specified element to an instance of T according
     * to the annotated fields in T.
     * 
     * @param element
     *            The XML element used as input
     * @return An instance of T with the mapped XML data or {@code null} if the
     *         element was {@code null}.
     */
    T readSingle(Element element);


    /**
     * Convert the data in the specified document to a list Ts according to the
     * annotated fields in T.
     * 
     * @param document
     *            The XML document used as input
     * @param xpath
     *            An xpath expression which should result in a list of elements
     *            which in turn are used to generated the instances of T
     * @return A list of T instances with the mapped XML data or an empty list
     *         if the document was {@code null}.
     */
    List<T> readList(Document document, String xpath);


    /**
     * Convert the data in the specified element to a list Ts according to the
     * annotated fields in T.
     * 
     * @param document
     *            The XML element used as input
     * @param xpath
     *            An xpath expression which should result in a list of elements
     *            which in turn are used to generated the instances of T
     * @return A list of T instances with the mapped XML data or an empty list
     *         if the element was {@code null}.
     */
    List<T> readList(Element element, String xpath);
}
