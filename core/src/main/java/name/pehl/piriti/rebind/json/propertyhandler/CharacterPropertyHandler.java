package name.pehl.piriti.rebind.json.propertyhandler;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;

import com.google.gwt.core.ext.TreeLogger;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2011-04-13 16:40:59 +0200 (Mi, 13 Apr 2011) $ $Revision: 364
 *          $
 */
public class CharacterPropertyHandler extends AbstractJsonPropertyHandler
{
    public CharacterPropertyHandler(TreeLogger logger)
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
        writer.write("%s = %s.stringValue();", propertyContext.getVariableNames().getValueAsStringVariable(),
                jsonString);
        writer.write("if (%1$s != null && %1$s.length() != 0) {", propertyContext.getVariableNames()
                .getValueAsStringVariable());
        writer.indent();
        writer.write("%s = %s.charAt(0);", propertyContext.getVariableNames().getValueVariable(), propertyContext
                .getVariableNames().getValueAsStringVariable());
        writer.outdent();
        writer.write("}");
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
        writer.write("%s.append(JsonUtils.escapeValue(String.valueOf(%s)));", propertyContext.getVariableNames()
                .getBuilderVariable(), propertyContext.getVariableNames().getValueVariable());
    }
}
