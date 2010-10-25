package name.pehl.piriti.rebind.xml.propertyhandler;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.propertyhandler.AbstractEnumPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * {@link PropertyHandler} for enum types. This implementation reads the XML
 * data as string and tries to convert it using
 * <code>enumType.valueOf(String)</code> .
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class EnumPropertyHandler extends AbstractEnumPropertyHandler
{
    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param propertyContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.xml.propertyhandler.ConverterPropertyHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public void readInput(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("String %s = %s.selectValue(\"%s\", %s);", propertyContext.getVariableNames()
                .getValueAsStringVariable(), propertyContext.getVariableNames().getInputVariable(), propertyContext
                .getPath(), propertyContext.isStripWsnl());
        writer.write("if (%s != null) {", propertyContext.getVariableNames().getValueAsStringVariable());
        writer.indent();
        if (propertyContext.isCustomConverter())
        {
            String converterVariable = propertyContext.getVariableNames().newVariableName("ReadConverter");
            writer.write("Converter<%1$s> %2$s = GWT.create(%3$s.class);", propertyContext.getType()
                    .getQualifiedSourceName(), converterVariable, propertyContext.getConverter().getName());
            writer.write("%s = %s.convert(%s, null);", propertyContext.getVariableNames().getValueVariable(),
                    converterVariable, propertyContext.getVariableNames().getValueAsStringVariable());
        }
        else
        {
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
        }
        writer.outdent();
        writer.write("}");
    }


    @Override
    public void markupStart(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("// markupStart() NYI");
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("// writeValue() NYI");
    }


    @Override
    public void markupEnd(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("// markupEnd() NYI");
    }
}
