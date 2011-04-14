package name.pehl.piriti.rebind.json.propertyhandler;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;

import com.google.gwt.core.ext.TreeLogger;

/**
 * {@link PropertyHandler} for enum types. This implementation reads the JSON
 * data as string and tries to convert it using
 * <code>enumType.valueOf(String)</code> .
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class EnumPropertyHandler extends SingleValuePropertyHandler
{
    public EnumPropertyHandler(TreeLogger logger)
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
        writer.write("if (%$1s != null && %$1s.stringValue() != null) {", jsonString);
        writer.indent();
        writer.write("try {");
        writer.indent();
        writer.write("%s = %s.valueOf(%s.stringValue());", propertyContext.getVariableNames().getValueVariable(),
                propertyContext.getEnumType().getQualifiedSourceName(), jsonString);
        writer.outdent();
        writer.write("}");
        writer.write("catch (IllegalArgumentException e1) {");
        writer.indent();
        writer.write("try {");
        writer.indent();
        writer.write("%s = %s.valueOf(%s.stringValue().toUpperCase());", propertyContext.getVariableNames()
                .getValueVariable(), propertyContext.getEnumType().getQualifiedSourceName(), jsonString);
        writer.outdent();
        writer.write("}");
        writer.write("catch (IllegalArgumentException e2) {}");
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
    protected String defaultValue()
    {
        return "null";
    }
}
