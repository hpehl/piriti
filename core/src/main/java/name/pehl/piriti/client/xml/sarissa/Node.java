package name.pehl.piriti.client.xml.sarissa;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A node that can work with Sarissa
 * 
 * @author Eric Bessette <ebessette@gmail.com>
 */
public class Node
{
    /**
     * The javascript version of the node
     */
    NodeImpl impl;


    /**
     * Creates a new Node
     * 
     * @param jso
     *            The javascript version of the node
     */
    private Node(JavaScriptObject jso)
    {
        this.impl = new NodeImpl(jso);
    }


    /**
     * Creates a new Node
     * 
     * @param n
     *            The javascript version of the node
     * @return The new node
     */
    public static Node create(JavaScriptObject n)
    {
        return new Node(n);
    }


    /**
     * Get a single child node
     * 
     * @param expr
     *            The xpath expression to get the child node
     * @return The node if found, null if not found. If more than one node
     *         matches, then only the first node will be returned
     */
    public Node getNode(String expr)
    {
        JavaScriptObject jso = this.impl.getNode(expr);
        if (jso == null)
        {
            return null;
        }
        return new Node(jso);
    }


    /**
     * Get all the child nodes that match the expression
     * 
     * @param expr
     *            The XPath expression
     * @return The child nodes, can be empty, but will not be null
     */
    public List<Node> getNodes(String expr)
    {
        List<Node> nodes = new ArrayList<Node>();
        this.impl.getNodes(expr, nodes);
        return nodes;
    }


    /**
     * Get the child nodes of this node
     * 
     * @return The child nodes
     */
    public List<Node> getChildNodes()
    {
        List<Node> nodes = new ArrayList<Node>();
        this.impl.getChildNodes(nodes);
        return nodes;
    }


    /**
     * Get the value of this node as a string
     * 
     * @return The node value
     */
    public String getStringValue()
    {
        return this.impl.getStringValue().trim();
    }


    /**
     * Get the value of this node as a double
     * 
     * @return The node value
     * @throws NumberFormatException
     *             Thrown if the value is not a number
     */
    public double getDoubleValue() throws NumberFormatException
    {
        String s = getStringValue();
        if (s == null || s.equals(""))
        {
            return Double.MIN_VALUE;
        }
        return Double.valueOf(s).doubleValue();
    }


    /**
     * Get the value of an attribute of this node
     * 
     * @param name
     *            The name of the attribute
     * @return The value of the attribute, empty if not found
     */
    public String getAttribute(String name)
    {
        return this.impl.getAttribute(name);
    }


    /**
     * Get the name of this node
     * 
     * @return The name
     */
    public String getNodeName()
    {
        return this.impl.getNodeName();
    }


    public String toString()
    {
        return this.impl.toString();
    }


    /**
     * Get the JavaScript node
     * 
     * @return The node
     */
    JavaScriptObject getJSNode()
    {
        return this.impl.jsNode;
    }
}
