package name.pehl.piriti.rebind.xml;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.VariableNames;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerLookup;
import name.pehl.piriti.rebind.xml.propertyhandler.XmlPropertyHandlerLookup;

import com.google.gwt.core.ext.TreeLogger;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2011-04-14 16:46:21 +0200 (Do, 14 Apr 2011) $ $Revision: 1224
 *          $
 */
public final class XmlUtils
{
    /**
     * Private constructor to ensure that the class acts as a true utility class
     * i.e. it isn't instantiable and extensible.
     */
    private XmlUtils()
    {
        super();
    }


    static VariableNames newVariableNames()
    {
        return new VariableNames("value", "Element", "input", "XmlRegistry", "xmlRegistry", "builder");
    }


    static PropertyHandlerLookup newPropertyHandlerLookup(TreeLogger logger)
    {
        return new XmlPropertyHandlerLookup(logger);
    }


    static void createImports(IndentedWriter writer)
    {
        writer.write("import name.pehl.piriti.xml.client.*;");
        writer.write("import name.pehl.totoe.xml.client.*;");
    }


    static void createConstructorBody(IndentedWriter writer)
    {
        writer.write("this.xmlRegistry = XmlGinjector.INJECTOR.getXmlRegistry();");
    }
}
