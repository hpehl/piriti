package name.pehl.piriti.rebind.json;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.VariableNames;
import name.pehl.piriti.rebind.json.propertyhandler.JsonPropertyHandlerLookup;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerLookup;

import com.google.gwt.core.ext.TreeLogger;

/**
 * @author $Author$
 * @version $Date$ $Revision: 1224
 *          $
 */
public final class JsonUtils
{
    /**
     * Private constructor to ensure that the class acts as a true utility class
     * i.e. it isn't instantiable and extensible.
     */
    private JsonUtils()
    {
        super();
    }


    static VariableNames newVariableNames()
    {
        return new VariableNames("value", "JSONObject", "input", "JsonReader", "JsonWriter", "JsonRegistry",
                "jsonRegistry", "builder");
    }


    static PropertyHandlerLookup newPropertyHandlerLookup(TreeLogger logger)
    {
        return new JsonPropertyHandlerLookup(logger);
    }


    static void createImports(IndentedWriter writer)
    {
        writer.write("import com.google.gwt.core.client.JsonUtils;");
        writer.write("import com.google.gwt.json.client.*;");
        writer.write("import name.pehl.piriti.json.client.*;");
        writer.write("import name.pehl.totoe.json.client.*;");
    }


    static void createConstructorBody(IndentedWriter writer)
    {
        writer.write("this.jsonRegistry = JsonGinjector.INJECTOR.getJsonRegistry();");
    }
}
