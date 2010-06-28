package name.pehl.piriti.client.xml.internal;

import java.util.ArrayList;
import java.util.List;

import name.pehl.piriti.client.xml.Node;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author $Author$
 * @version $Date$ $Revision: 623
 *          $
 */
public class SarissaNodeImpl implements Node
{
    private JavaScriptObject jsNode;


    // ----------------------------------------------------------- constructors

    private SarissaNodeImpl(JavaScriptObject jso)
    {
        this.jsNode = jso;
    }


    public static Node create(JavaScriptObject jso)
    {
        return new SarissaNodeImpl(jso);
    }


    // -------------------------------------------------- basic node operations

    @Override
    public native String getNodeName() /*-{
        var node = this.@name.pehl.piriti.client.xml.internal.SarissaNodeImpl::jsNode;
        if (!node) 
        {
            return null;
        }
        return node.nodeName;
    }-*/;


    @Override
    public native String getNodeValue() /*-{
        var value = null;
        return this.@name.pehl.piriti.client.xml.internal.SarissaNodeImpl::jsNode;
    }-*/;


    @Override
    public native String getAttribute(String name) /*-{
        var node = this.@name.pehl.piriti.client.xml.internal.SarissaNodeImpl::jsNode;
        return node.getAttribute(name);
    }-*/;


    // ----------------------------------------------- nodes as direct children

    @Override
    public List<Node> getChildNodes(Node node)
    {
        List<Node> result = new ArrayList<Node>();
        getChildNodesImpl(node, result);
        return result;
    }


    private native void getChildNodesImpl(Node node, List<Node> result) /*-{
        var node = this.@name.pehl.piriti.client.xml.internal.SarissaNodeImpl::jsNode;
        var children = node.childNodes;
        if (children != null)
        {
            for (var i = 0; i < children.length; i++) 
            {
                var n = children[i];
                result.@java.util.List::add(Ljava/lang/Object;)(@name.pehl.piriti.client.xml.internal.SarissaNodeImpl::create(Lcom/google/gwt/core/client/JavaScriptObject;)(n));
            }
        }
    }-*/;


    // ------------------------------------------------------- node(s) by xpath

    @Override
    public native List<Node> selectNodes(Node node, String xpath)/*-{
        var result = @java.util.ArrayList::new()();
        var node = this.@name.pehl.piriti.client.xml.internal.SarissaNodeImpl::jsNode;
        var nodes = node.selectNodes(expr);

        return node.childNodes;
    }-*/;


    @Override
    public Node selectNode(Node node, String xpath)
    {
        // TODO Implement me!
        throw new UnsupportedOperationException("Not implemented");
    }


    // ------------------------------------------------------ value(s) by xpath

    @Override
    public String[] selectValues(Node node, String xpath)
    {
        // TODO Implement me!
        throw new UnsupportedOperationException("Not implemented");
    }


    @Override
    public String selectValue(Node node, String xpath)
    {
        // TODO Implement me!
        throw new UnsupportedOperationException("Not implemented");
    }
}
