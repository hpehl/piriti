package name.pehl.piriti.client.xml;

import java.util.List;

import com.google.inject.internal.Nullable;

/**
 * Interface for converting an XML document / element to an instance of T or a
 * list of Ts. The implementation for this interface is generated using deferred
 * binding. All fields of T which are annotated with {@link XmlField} are
 * handled by the generated XmlReader implementation. If the xpath expression
 * behind {@link XmlField} returns some data != null the relevant field is
 * assigned with the converted value, otherwise the field remains unchanged.
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
 * SmartYakizakanaState sys = SmartYakizakanaState.XML.read(document);
 * </pre>
 * 
 * @param <T>
 *            The type
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 46 $
 */
public interface XmlReader<T>
{
    // -------------------------------------------------------------- read list

    /**
     * Convert the direct children of the node to a list of Ts according to the
     * annotated fields in T.
     * 
     * @param node
     *            The node used as input. May be <code>null</code>.
     * @return A list of T instances with the mapped XML data or an empty list
     *         if the node was {@code null}.
     */
    List<T> readList(@Nullable Node node);


    /**
     * Convert the data in the specified node to a list of Ts according to the
     * annotated fields in T.
     * 
     * @param node
     *            The node used as input. May be <code>null</code>.
     * @param xpath
     *            An xpath expression which should result in a list of elements
     *            which in turn is used to generate the instances of T
     * @return A list of T instances with the mapped XML data or an empty list
     *         if the node was {@code null}.
     */
    List<T> readList(@Nullable Node node, String xpath);


    // ------------------------------------------------------------ read single

    /**
     * Convert the data in the specified node to an instance of T according to
     * the annotated fields in T.
     * 
     * @param node
     *            The node used as input. May be <code>null</code>.
     * @return An instance of T with the mapped XML data or {@code null} if the
     *         node was {@code null}.
     */
    T read(@Nullable Node node);


    /**
     * Convert the data in the specified element to an instance of T according
     * to the annotated fields in T.
     * 
     * @param node
     *            The node used as input. May be <code>null</code>.
     * @param xpath
     *            An xpath expression which should result in a single element
     *            which in turn is used to generate the instance of T
     * @return An instance of T with the mapped XML data or {@code null} if the
     *         node was {@code null}.
     */
    T read(@Nullable Node node, String xpath);


    // ------------------------------------------------------------- references

    /**
     * Returns the reference for the specified identifier or <code>null</code>
     * if no reference was found.
     * 
     * @param id
     * @return
     */
    T idRef(String id);

    // ------------------------------------------------------------- namespace

    /**
     * The prefix which has to be used in xpath selectors for the default
     * namespace. Please note: The prefix does <strong>not</strong> contain the
     * colon (:).
     * <p>
     * Usage:
     * 
     * <pre>
     * import static name.pehl.piriti.client.xml.XmlReader.*;
     * ...
     * public class Meep
     * {
     *     public interface MeepReader extends XmlReader&lt;Meep&gt; {}
     *     public static final MeepReader XML = GWT.create(MeepReader.class);
     *     static
     *     {
     *         XML.registerNamespace("http://www.acme.org/dingus");
     *     }
     *     
     *     &#64;XmlField(DNS + ":foo/" + DNS + ":bar/text()")
     *     String fooBar;
     * }
     * </pre>
     */
    String DNS = "piriti_default_ns_prefix";


    /**
     * Registers the specified namespace as default namespace.
     * 
     * @param uri
     */
    void registerNamespace(String uri);


    /**
     * Registers the specified prefix with the namespace.
     * 
     * @param prefix
     * @param uri
     */
    void registerNamespace(String prefix, String uri);
}
