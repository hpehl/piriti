package name.pehl.piriti.rebind.json.propertyhandler;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.TypeUtils;

import com.google.gwt.core.ext.TreeLogger;

/**
 * @author $Author$
 * @version $Date$ $Revision: 364
 *          $
 */
public class NumericPropertyHandler extends AbstractJsonPropertyHandler
{
    public NumericPropertyHandler(TreeLogger logger)
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
        String jsonNumber = propertyContext.getVariableNames().newVariableName("AsJsonNumber");
        writer.write("JSONNumber %s = %s.isNumber();", jsonNumber, jsonValueVariable);
        writer.write("if (%s != null) {", jsonNumber);
        writer.indent();
        String doubleValue = propertyContext.getVariableNames().getValueVariable() + "AsDouble";
        writer.write("Double %s = new Double(%s.doubleValue());", doubleValue, jsonNumber);
        if (TypeUtils.isByte(propertyContext.getType()))
        {
            writer.write("%s = %s.byteValue();", propertyContext.getVariableNames().getValueVariable(), doubleValue);
        }
        else if (TypeUtils.isShort(propertyContext.getType()))
        {
            writer.write("%s = %s.shortValue();", propertyContext.getVariableNames().getValueVariable(), doubleValue);
        }
        else if (TypeUtils.isInteger(propertyContext.getType()))
        {
            writer.write("%s = %s.intValue();", propertyContext.getVariableNames().getValueVariable(), doubleValue);
        }
        else if (TypeUtils.isLong(propertyContext.getType()))
        {
            writer.write("%s = %s.longValue();", propertyContext.getVariableNames().getValueVariable(), doubleValue);
        }
        else if (TypeUtils.isFloat(propertyContext.getType()))
        {
            writer.write("%s = %s.floatValue();", propertyContext.getVariableNames().getValueVariable(), doubleValue);
        }
        else if (TypeUtils.isDouble(propertyContext.getType()))
        {
            writer.write("%s = %s;", propertyContext.getVariableNames().getValueVariable(), doubleValue);
        }
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
        return "0";
    }
}
