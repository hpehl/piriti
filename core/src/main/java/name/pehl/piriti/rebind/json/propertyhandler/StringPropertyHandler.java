package name.pehl.piriti.rebind.json.propertyhandler;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;

import com.google.gwt.core.ext.TreeLogger;

/**
 * Simple {@link PropertyHandler} implementation for strings.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class StringPropertyHandler extends SingleValuePropertyHandler
{
    public StringPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    @Override
    protected void readInputDirectly(IndentedWriter writer, PropertyContext propertyContext)
    {
        String jsonValue = getOrSelectJson(writer, propertyContext);
        writer.write("if (%s != null) {", jsonValue);
        writer.indent();
        writer.write("if (%s.isNull() == null) {", jsonValue);
        writer.indent();
        String jsonString = propertyContext.getVariableNames().newVariableName("AsJsonString");
        writer.write("JSONString %s = %s.isString();", jsonString, jsonValue);
        writer.write("if (%s != null) {", jsonString);
        writer.indent();
        writer.write("%s = %s.stringValue();", propertyContext.getVariableNames().getValueVariable(), jsonString);
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }


    @Override
    protected String defaultValue()
    {
        return "null";
    }
}
