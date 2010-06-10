package name.pehl.piriti.client.xml;

import java.util.List;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;

/**
 * Interface for parsing xpath expressions and returning relevant information.
 * 
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public interface XPath
{
    // -------------------------------------------- elements as direct children

    /**
     * Returns all elements which are direct children of the documents root
     * element.
     * 
     * @param document
     * @return all elements which are direct children of the documents root
     *         element or <code>null</code> if the document is null or if the
     *         documents root element is empty.
     */
    List<Element> getElements(Document document);


    /**
     * Returns all elements which are direct children of the specified element.
     * 
     * @param element
     * @return all elements which are direct children of the specified element
     *         or <code>null</code> if the element is null or empty.
     */
    List<Element> getElements(Element element);


    // ---------------------------------------------------- element(s) by xpath

    /**
     * Returns a list of elements for the specifed xpath.
     * 
     * @param document
     * @param xpath
     * @return The list of elements matching the xpath or an empty list if no
     *         matching elements were found
     */
    List<Element> getElements(Document document, String xpath);


    /**
     * Returns a list of elements for the specifed xpath.
     * 
     * @param element
     * @param xpath
     * @return The list of elements matching the xpath or an empty list if no
     *         matching elements were found
     */
    List<Element> getElements(Element element, String xpath);


    /**
     * Returns the element for the specifed xpath.
     * 
     * @param document
     * @param xpath
     * @return The element matching the xpath or {@code null} if no matching
     *         element was found
     */
    Element getElement(Document document, String xpath);


    /**
     * Returns the element for the specifed xpath.
     * 
     * @param xpath
     * @return The element matching the xpath or {@code null} if no matching
     *         element was found
     */
    Element getElement(Element element, String xpath);


    // ------------------------------------------------------ value(s) by xpath

    /**
     * Returns the string values for the specified xpath. The nodes selected by
     * the xpath expression must be attribute or a text nodes.
     * 
     * @param document
     * @param xpath
     * @return The string values matched by the xpath or {@code null} if the
     *         xpath does not resolve to attribute or text nodes
     */
    String[] getValues(Document document, String xpath);


    /**
     * Returns the string values for the specified xpath. The nodes selected by
     * the xpath expression must be attribute or a text nodes.
     * 
     * @param document
     * @param xpath
     * @return The string values matched by the xpath or {@code null} if the
     *         xpath does not resolve to attribute or text nodes
     */
    String[] getValues(Element element, String xpath);


    /**
     * Returns the string value for the specified xpath. The node selected by
     * the xpath expression must be an attribute or a text node.
     * 
     * @param document
     * @param xpath
     * @return The string value matched by the xpath or {@code null} if the
     *         xpath does not resolve to an attribute or text node
     */
    String getValue(Document document, String xpath);


    /**
     * Returns the string value for the specified xpath. The node selected by
     * the xpath expression must be an attribute or a text node.
     * 
     * @param element
     * @param xpath
     * @return The string value matched by the xpath or {@code null} if the
     *         xpath does not resolve to an attribute or text node
     */
    String getValue(Element element, String xpath);
}
