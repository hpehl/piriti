/**
 * Created on Aug 19, 2006
 */
package name.pehl.piriti.client.sarissa;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Wraps the XML functions from Sarissa, http://sarissa.sourceforge.net/doc/
 * 
 * @author Eric Bessette <ebessette@gmail.com>
 */
public class XML
{
    /**
     * The DOM parser
     */
    private static final JavaScriptObject domParser = XML.initialize();


    /**
     * Parse a string into an XML DOM
     * 
     * @param xmlString
     *            An xml node as a string
     * @return The DOM representation for the XML node
     * @throws XMLParseException
     *             Thrown if a parsing error occurs
     */
    public static Node parse(String xmlString) throws XMLParseException
    {
        if (XML.domParser == null)
        {
            throw new XMLParseException("No parser is available.");
        }

        Node n = null;
        try
        {
            n = parseImpl(xmlString);
        }
        catch (Exception e)
        {
            throw new XMLParseException(e.getMessage());
        }
        return n;
    }


    /**
     * Parse a string into an XML DOM
     * 
     * @param xmlString
     *            An xml node as a string
     * @return The DOM representation for the XML node
     */
    private static native Node parseImpl(String xmlString) /*-{
        var domDoc = @name.pehl.piriti.client.sarissa.XML::domParser.parseFromString( xmlString, "text/xml" );

        var error = $wnd.Sarissa.getParseErrorText( domDoc );
        if ( error != $wnd.Sarissa.PARSED_OK ) {
        if ( error == $wnd.Sarissa.PARSED_EMPTY ) {
        throw( new Error( "XML file is empty." ) );
        }
        else if ( error == $wnd.Sarissa.PARSED_UNKNOWN_ERROR ) {
        throw( new Error( "Unkown error parsing xml file." ) );
        }
        else {
        throw( new Error( error ) );
        }

        return null;
        }

        domDoc.setProperty("SelectionNamespaces", "xmlns:xsl='http://www.w3.org/1999/XSL/Transform'");
        domDoc.setProperty("SelectionLanguage", "XPath");

        return @name.pehl.piriti.client.sarissa.Node::create(Lcom/google/gwt/core/client/JavaScriptObject;)( domDoc );
    }-*/;


    /**
     * Initialize the XML parser
     * 
     * @return A new xml parser
     */
    private static native JavaScriptObject initialize() /*-{
        return new $wnd.DOMParser();
    }-*/;
}
