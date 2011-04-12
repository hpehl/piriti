package name.pehl.piriti.rebind.xml.propertyhandler;

import name.pehl.piriti.commons.client.WhitespaceHandling;
import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.AbstractEnumPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;

import com.google.gwt.core.ext.TreeLogger;
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
    public EnumPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


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
    public void readInput(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerRegistry propertyHandlerRegistry) throws UnableToCompleteException
    {
        writer.write("String %s = %s.selectValue(\"%s\", %s);", propertyContext.getVariableNames()
                .getValueAsStringVariable(), propertyContext.getVariableNames().getInputVariable(), propertyContext
                .getPath(), propertyContext.getWhitespaceHandling() == WhitespaceHandling.REMOVE);
        writer.write("if (%s != null) {", propertyContext.getVariableNames().getValueAsStringVariable());
        writer.indent();
        if (propertyContext.useCustomConverter())
        {
            CodeGeneration.useConverterForReading(writer, propertyContext);
        }
        else
        {
            writer.write("try {");
            writer.indent();
            writer.write("%s = %s.valueOf(%s);", propertyContext.getVariableNames().getValueVariable(), propertyContext
                    .getEnumType().getQualifiedSourceName(), propertyContext.getVariableNames()
                    .getValueAsStringVariable());
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


    /**
     * @param writer
     * @param propertyContext
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler#readInputAsString(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.PropertyContext)
     */
    @Override
    protected void readInputAsString(IndentedWriter writer, PropertyContext propertyContext)
    {
        // TODO Implement me!
    }


    @Override
    public void markupStart(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("// markupStart() NYI");
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerRegistry propertyHandlerRegistry) throws UnableToCompleteException
    {
        writer.write("// writeValue() NYI");
    }


    @Override
    public void markupEnd(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("// markupEnd() NYI");
    }
}
