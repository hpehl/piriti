/**
 * Created on Sep 4, 2006
 */
package name.pehl.piriti.client.sarissa;

import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * The JSNI implemention for a Node
 * 
 * @author Eric Bessette <ebessette@gmail.com>
 */
class NodeImpl
{
    /**
     * The javascript version of the node
     */
    JavaScriptObject jsNode;


    /**
     * Creates a new Node Implemenation
     * 
     * @param jso
     *            The javascript version of the node
     */
    public NodeImpl(JavaScriptObject jso)
    {
        this.jsNode = jso;
    }


    /**
     * Get a single child node
     * 
     * @param expr
     *            The xpath expression to get the child node
     * @return The node if found, null if not found. If more than one node
     *         matches, then only the first node will be returned
     */
    public native JavaScriptObject getNode(String expr) /*-{
        var node = this.@name.pehl.piriti.client.sarissa.NodeImpl::jsNode;

        var n = node.selectSingleNode( expr );

        if ( !n || n == undefined ) {
        return null;
        }

        return n;
    }-*/;


    /**
     * Get all the child nodes that match the expression
     * 
     * @param expr
     *            The XPath expression
     * @param nodeList
     *            The node list to populate with the child nodes
     */
    public native void getNodes(String expr, List<Node> nodeList) /*-{
        if ( nodeList == null ) {
        return;
        }

        var node = this.@name.pehl.piriti.client.sarissa.NodeImpl::jsNode;

        var nodeSet = node.selectNodes( expr );

        for ( var i = 0; i < nodeSet.length; i++ ) {
        var n = nodeSet[ i ];
        nodeList.@java.util.List::add(Ljava/lang/Object;)( @name.pehl.piriti.client.sarissa.Node::create(Lcom/google/gwt/core/client/JavaScriptObject;)( n ) );
        }
    }-*/;


    /**
     * Get the value of this node as a string
     * 
     * @return The node value
     */
    public native String getStringValue() /*-{
        var node = this.@name.pehl.piriti.client.sarissa.NodeImpl::jsNode;

        if ( !node ) {
        return null;
        }
        else if ( node.childNodes.length == 1 ) {
        return node.childNodes[ 0 ].nodeValue;
        }
        else if ( !node.nodeValue ) {
        return "";
        }
        else {
        return node.nodeValue;
        }
    }-*/;


    /**
     * Get the value of an attribute of this node
     * 
     * @param name
     *            The name of the attribute
     * @return The value of the attribute, empty if not found
     */
    public native String getAttribute(String name) /*-{
        var node = this.@name.pehl.piriti.client.sarissa.NodeImpl::jsNode;

        var value = node.getAttribute( name );

        if ( value == null ) {
        return "";
        }
        else {
        return value;
        }
    }-*/;


    /**
     * Get the name of this node
     * 
     * @return The name
     */
    public native String getNodeName() /*-{
        var node = this.@name.pehl.piriti.client.sarissa.NodeImpl::jsNode;

        return node.nodeName;
    }-*/;


    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public native String toString() /*-{
        var node = this.@name.pehl.piriti.client.sarissa.NodeImpl::jsNode;

        return node.xml;
    }-*/;


    /**
     * Get the child nodes for this node
     * 
     * @param nodeList
     *            The list to populate
     */
    public native void getChildNodes(List<Node> nodeList) /*-{
        var node = this.@name.pehl.piriti.client.sarissa.NodeImpl::jsNode;

        for ( i = 0; i < node.childNodes.length; i++ ) {
        var n = node.childNodes[ i ];
        nodeList.@java.util.List::add(Ljava/lang/Object;)( @name.pehl.piriti.client.sarissa.Node::create(Lcom/google/gwt/core/client/JavaScriptObject;)( n ) );
        }
    }-*/;
}
