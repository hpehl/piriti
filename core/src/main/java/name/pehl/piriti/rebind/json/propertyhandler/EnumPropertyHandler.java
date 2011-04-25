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
public class EnumPropertyHandler extends AbstractJsonPropertyHandler
{
    public EnumPropertyHandler(TreeLogger logger)
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
        writer.write("if (%1$s != null && %1$s.stringValue() != null) {", jsonString);
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
    protected void writeValueDirectly(IndentedWriter writer, PropertyContext propertyContext)
    {
        writer.write("%s.append(JsonUtils.escapeValue(%s.name()));", propertyContext.getVariableNames()
                .getBuilderVariable(), propertyContext.getVariableNames().getValueVariable());
    }
}
