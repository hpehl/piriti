package name.pehl.piriti.rebind.json.propertyhandler;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;

import com.google.gwt.core.ext.TreeLogger;

/**
 * @author $Author$
 * @version $Date$ $Revision: 364
 *          $
 */
public class BooleanPropertyHandler extends AbstractJsonPropertyHandler
{
    public BooleanPropertyHandler(TreeLogger logger)
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
        String jsonBoolean = propertyContext.getVariableNames().newVariableName("AsJsonBoolean");
        writer.write("JSONBoolean %s = %s.isBoolean();", jsonBoolean, jsonValueVariable);
        writer.write("if (%s != null) {", jsonBoolean);
        writer.indent();
        writer.write("%s = %s.booleanValue();", propertyContext.getVariableNames().getValueVariable(), jsonBoolean);
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
        return "false";
    }
}
