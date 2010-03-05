package name.pehl.gwt.piriti.rebind.xml;

import name.pehl.gwt.piriti.rebind.AbstractFieldHandler;
import name.pehl.gwt.piriti.rebind.FieldContext;
import name.pehl.gwt.piriti.rebind.FieldHandler;
import name.pehl.gwt.piriti.rebind.IndentedWriter;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Default implementation for a {@link FieldHandler}.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class DefaultFieldHandler extends AbstractFieldHandler
{
    /**
     * Returns always <code>true</code>.
     * 
     * @param writer
     * @param fieldContext
     * @return always <code>true</code>
     * @see name.pehl.gwt.piriti.rebind.AbstractFieldHandler#isValid(name.pehl.gwt.piriti.rebind.FieldContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        return true;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void writeComment(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("// Handle %s", fieldContext);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void writeDeclaration(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("%s %s = null;", fieldContext.getFieldType().getParameterizedQualifiedSourceName(), fieldContext
                .getValueVariable());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void writeConverterCode(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("String %s = XPathUtils.getValue(%s, \"%s\");", fieldContext.getValueAsStringVariable(),
                fieldContext.getInputVariable(), fieldContext.getPath());
        writer.write("if (%s != null) {", fieldContext.getValueAsStringVariable());
        writer.indent();
        writer.write("Converter<%1$s> converter = converterRegistry.get(%1$s.class);", fieldContext.getFieldType()
                .getQualifiedSourceName());
        writer.write("if (converter != null) {");
        writer.indent();
        if (fieldContext.getFormat() != null)
        {
            writer.write("%s = converter.convert(%s, \"%s\");", fieldContext.getValueVariable(), fieldContext
                    .getValueAsStringVariable(), fieldContext.getFormat());
        }
        else
        {
            writer.write("%s = converter.convert(%s, null);", fieldContext.getValueVariable(), fieldContext
                    .getValueAsStringVariable());
        }
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void writeAssignment(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("if (%s != null) {", fieldContext.getValueVariable());
        writer.indent();
        writer.write("model.%s = %s;", fieldContext.getFieldName(), fieldContext.getValueVariable());
        writer.outdent();
        writer.write("}");
    }
}
