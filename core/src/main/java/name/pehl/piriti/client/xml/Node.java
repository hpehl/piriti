package name.pehl.piriti.client.xml;

import java.util.List;

/**
 * @author $Author$
 * @version $Date$ $Revision: 623
 *          $
 */
public interface Node
{
    // -------------------------------------------------- basic node operations

    /**
     * Get the name of this node.
     * 
     * @return The name
     */
    String getNodeName();


    /**
     * Get the value of this node as a string. If this node is a text node, the
     * text is returned. In case of an attribute the value of the attribute is
     * returned. For an element <code>null</code> is returned.
     * 
     * @return The node value.
     */
    String getNodeValue();


    /**
     * Returns the type of this node.
     * 
     * @return The node type.
     */
    NodeType getNodeType();


    /**
     * Get the value of an attribute of this node.
     * 
     * @param name
     *            The name of the attribute
     * @return The value of the attribute, <code>null</code> if not found.
     */
    String getAttribute(String name);


    /**
     * Returns the root node of the document in case the current node is the
     * document node. If the current node is not the document node,
     * <code>null</code> is returned.
     * 
     * @return the root node if the current node is the document node,
     *         <code>null</code> otherwise.
     */
    Node getRoot();


    // ----------------------------------------------- nodes as direct children

    /**
     * Returns all nodes which are direct children of the specified node.
     * Returns an empty list if the node has no children.
     * 
     * @return all nodes which are direct children of the specified node or an
     *         empty list, if the node is null or has no children.
     */
    List<Node> getChildNodes();


    // ------------------------------------------------------- node(s) by xpath

    /**
     * Returns a list of nodes for the specifed xpath or an empty list if
     * matching nodes were found.
     * 
     * @param xpath
     * @return The list of nodes matching the xpath or an empty list if no
     *         matching nodes were found.
     */
    List<Node> selectNodes(String xpath);


    /**
     * Returns the node for the specifed xpath or <code>null</code> if no
     * mathing node was found.
     * 
     * @param xpath
     * @return The node matching the xpath or <code>null</code> if no matching
     *         element was found.
     */
    Node selectNode(String xpath);


    // ------------------------------------------------------ value(s) by xpath

    /**
     * Returns the string values for the specified xpath. The nodes selected by
     * the xpath expression must be attribute or a text nodes. Returns an empty
     * array if no mathing nodes were found.
     * 
     * @param xpath
     * @return The string values matched by the xpath or an empty array if no
     *         mathing nodes were found.
     */
    String[] selectValues(String xpath);


    /**
     * Returns the string value for the specified xpath. The node selected by
     * the xpath expression must be an attribute or a text node.
     * 
     * @param xpath
     * @return The string value matched by the xpath or <code>null</code> if no
     *         mathing node were found.
     */
    String selectValue(String xpath);
}
