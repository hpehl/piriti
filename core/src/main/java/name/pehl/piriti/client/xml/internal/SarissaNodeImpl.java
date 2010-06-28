package name.pehl.piriti.client.xml.internal;

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
    @SuppressWarnings("unused")
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
    public native String getNodeName()/*-{
        var node = this.@name.pehl.piriti.client.xml.internal.SarissaNodeImpl::jsNode;
        if (!node) 
        {
            return null;
        }
        return node.nodeName;
    }-*/;


    @Override
    public native String getNodeValue()/*-{
        var value = null;
        var node = this.@name.pehl.piriti.client.xml.internal.SarissaNodeImpl::jsNode;
        if (node != null) 
        {
            if (node.childNodes.length == 1)
            {
                value = node.childNodes[0].nodeValue;
            }
            else
            {
                value = node.nodeValue;
            }
        }
        return value;
    }-*/;


    @Override
    public native String getAttribute(String name)/*-{
        var node = this.@name.pehl.piriti.client.xml.internal.SarissaNodeImpl::jsNode;
        return node.getAttribute(name);
    }-*/;


    // ----------------------------------------------- nodes as direct children

    @Override
    public native List<Node> getChildNodes(Node node)/*-{
    var node = this.@name.pehl.piriti.client.xml.internal.SarissaNodeImpl::jsNode;
    return node.childNodes;
}-*/;


    // ------------------------------------------------------- node(s) by xpath

    @Override
    public List<Node> selectNodes(Node node, String xpath)
    {
        // TODO Implement me!
        throw new UnsupportedOperationException("Not implemented");
    }


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
