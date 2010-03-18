package name.pehl.piriti.gxt.client.xml;

import java.util.List;

import com.extjs.gxt.ui.client.data.ModelData;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.inject.internal.Nullable;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public interface XmlModelReader<T extends ModelData>
{
    /**
     * Convert the data in the specified document to an instance of T according
     * to the annotated fields in T.
     * 
     * @param document
     *            The XML document used as input. May be <code>null</code>.
     * @return An instance of T with the mapped XML data or {@code null} if the
     *         document was {@code null}.
     */
    T read(@Nullable Document document);


    /**
     * Convert the data in the specified element to an instance of T according
     * to the annotated fields in T.
     * 
     * @param element
     *            The XML element used as input. May be <code>null</code>.
     * @return An instance of T with the mapped XML data or {@code null} if the
     *         element was {@code null}.
     */
    T read(@Nullable Element element);


    /**
     * Convert the data in the specified document to a list of Ts according to
     * the annotated fields in T.
     * 
     * @param document
     *            The XML document used as input. May be <code>null</code>.
     * @param xpath
     *            An xpath expression which should result in a list of elements
     *            which in turn is used to generated the instances of T
     * @return A list of T instances with the mapped XML data or an empty list
     *         if the document was {@code null}.
     */
    List<T> readList(@Nullable Document document, String xpath);


    /**
     * Convert the data in the specified element to a list of Ts according to
     * the annotated fields in T.
     * 
     * @param document
     *            The XML element used as input. May be <code>null</code>.
     * @param xpath
     *            An xpath expression which should result in a list of elements
     *            which in turn are used to generated the instances of T
     * @return A list of T instances with the mapped XML data or an empty list
     *         if the element was {@code null}.
     */
    List<T> readList(@Nullable Element element, String xpath);
}
