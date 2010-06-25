package name.pehl.piriti.client.xml;

import java.util.List;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public interface Node
{
    // -------------------------------------------------- basic node operations

    /**
     * Get the name of this node
     * 
     * @return The name
     */
    public String getNodeName();


    /**
     * Get the value of this node as a string
     * 
     * @return The node value
     */
    public String getNodeValue();


    /**
     * Get the value of an attribute of this node
     * 
     * @param name
     *            The name of the attribute
     * @return The value of the attribute, <code>null</code> if not found
     */
    public String getAttribute(String name);


    // ----------------------------------------------- nodes as direct children

    /**
     * Returns all nodes which are direct children of the specified node.
     * 
     * @param node
     * @return all nodes which are direct children of the specified node or
     *         <code>null</code> if the node is null or has no children.
     */
    List<Node> getChildNodes(Node node);


    // ------------------------------------------------------- node(s) by xpath

    /**
     * Returns a list of nodes for the specifed xpath.
     * 
     * @param node
     * @param xpath
     * @return The list of nodes matching the xpath or an empty list if no
     *         matching elements were found
     */
    List<Node> selectNodes(Node node, String xpath);


    /**
     * Returns the node for the specifed xpath.
     * 
     * @param node
     * @param xpath
     * @return The node matching the xpath or {@code null} if no matching
     *         element was found
     */
    Node selectNode(Node node, String xpath);


    // ------------------------------------------------------ value(s) by xpath

    /**
     * Returns the string values for the specified xpath. The nodes selected by
     * the xpath expression must be attribute or a text nodes.
     * 
     * @param node
     * @param xpath
     * @return The string values matched by the xpath or {@code null} if the
     *         xpath does not resolve to attribute or text nodes
     */
    String[] selectValues(Node node, String xpath);


    /**
     * Returns the string value for the specified xpath. The node selected by
     * the xpath expression must be an attribute or a text node.
     * 
     * @param node
     * @param xpath
     * @return The string value matched by the xpath or {@code null} if the
     *         xpath does not resolve to an attribute or text node
     */
    String selectValue(Node node, String xpath);
}
