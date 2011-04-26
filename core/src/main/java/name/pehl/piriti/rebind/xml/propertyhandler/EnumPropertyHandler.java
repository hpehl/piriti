package name.pehl.piriti.rebind.xml.propertyhandler;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;

import com.google.gwt.core.ext.TreeLogger;

/**
 * {@link PropertyHandler} for enum types. This implementation reads the XML
 * data as string and tries to convert it using
 * <code>enumType.valueOf(String)</code> .
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class EnumPropertyHandler extends AbstractXmlPropertyHandler
{
    public EnumPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    @Override
    protected void readInputDirectly(IndentedWriter writer, PropertyContext propertyContext)
    {
        writer.write("if (%s != null) {", propertyContext.getVariableNames().getValueAsStringVariable());
        writer.indent();
        writer.write("try {");
        writer.indent();
        writer.write("%s = %s.valueOf(%s);", propertyContext.getVariableNames().getValueVariable(), propertyContext
                .getEnumType().getQualifiedSourceName(), propertyContext.getVariableNames().getValueAsStringVariable());
        writer.outdent();
        writer.write("}");
        writer.write("catch (IllegalArgumentException e1) {");
        writer.indent();
        writer.write("try {");
        writer.indent();
        writer.write("%s = %s.valueOf(%s.toUpperCase());", propertyContext.getVariableNames().getValueVariable(),
                propertyContext.getEnumType().getQualifiedSourceName(), propertyContext.getVariableNames()
                        .getValueAsStringVariable());
        writer.outdent();
        writer.write("}");
        writer.write("catch (IllegalArgumentException e2) {}");
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
