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
public class StringPropertyHandler extends AbstractJsonPropertyHandler
{
    public StringPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    @Override
    protected void readInputDirectly(IndentedWriter writer, PropertyContext propertyContext)
    {
        writer.write("if (%s != null) {", jsonValueVariable);
        writer.indent();
        writer.write("if (%s.isNull() == null) {", jsonValueVariable);
        writer.indent();
        String jsonString = propertyContext.getVariableNames().newVariableName("AsJsonString");
        writer.write("JSONString %s = %s.isString();", jsonString, jsonValueVariable);
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
    protected void writeValueDirectly(IndentedWriter writer, PropertyContext propertyContext)
    {
        writer.write("%s.append(JsonUtils.escapeValue(%s));", propertyContext.getVariableNames().getBuilderVariable(),
                propertyContext.getVariableNames().getValueVariable());
    }
}
