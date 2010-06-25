package name.pehl.piriti.client.xml.internal;

import java.util.List;

import name.pehl.piriti.client.xml.Node;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
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
    public String getNodeName()
    {
        // TODO Implement me!
        throw new UnsupportedOperationException("Not implemented");
    }


    @Override
    public String getNodeValue()
    {
        // TODO Implement me!
        throw new UnsupportedOperationException("Not implemented");
    }


    @Override
    public String getAttribute(String name)
    {
        // TODO Implement me!
        throw new UnsupportedOperationException("Not implemented");
    }


    // ----------------------------------------------- nodes as direct children

    @Override
    public List<Node> getChildNodes(Node node)
    {
        // TODO Implement me!
        throw new UnsupportedOperationException("Not implemented");
    }


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
