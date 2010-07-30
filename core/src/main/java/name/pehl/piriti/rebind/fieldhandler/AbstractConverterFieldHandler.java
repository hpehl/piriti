package name.pehl.piriti.rebind.fieldhandler;

import name.pehl.piriti.client.converter.Converter;
import name.pehl.piriti.client.converter.ConverterRegistry;
import name.pehl.piriti.rebind.IndentedWriter;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * {@link FieldHandler} implementation which uses a {@link Converter} from the
 * {@link ConverterRegistry}.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public abstract class AbstractConverterFieldHandler extends AbstractFieldHandler
{
    /**
     * Returns always <code>true</code>.
     * 
     * @param writer
     * @param fieldContext
     * @return always <code>true</code>
     * @see name.pehl.piriti.rebind.fieldhandler.AbstractFieldHandler#isValid(name.pehl.piriti.rebind.fieldhandler.FieldContext)
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
    public void readInput(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        readInputAsString(writer, fieldContext);
        writer.write("if (%s != null) {", fieldContext.getValueAsStringVariable());
        writer.indent();
        String converterVariable = fieldContext.newVariableName("ReadConverter");
        writer.write("Converter<%1$s> %2$s = converterRegistry.get(%1$s.class);", fieldContext.getFieldType()
                .getQualifiedSourceName(), converterVariable);
        writer.write("if (%s != null) {", converterVariable);
        writer.indent();
        if (fieldContext.getFormat() != null)
        {
            writer.write("%s = %s.convert(%s, \"%s\");", fieldContext.getValueVariable(), converterVariable,
                    fieldContext.getValueAsStringVariable(), fieldContext.getFormat());
        }
        else
        {
            writer.write("%s = %s.convert(%s, null);", fieldContext.getValueVariable(), converterVariable,
                    fieldContext.getValueAsStringVariable());
        }
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }


    /**
     * Responsible to read the value as string and assigning it to
     * {@link FieldContext#getValueAsStringVariable()}.
     * 
     * @param writer
     * @param fieldContext
     */
    protected abstract void readInputAsString(IndentedWriter writer, FieldContext fieldContext);


    /**
     * Converts the {@link FieldContext#getValueVariable()} to a String
     * represented by {@link FieldContext#getValueAsStringVariable()} using a
     * registered converter. If no converter for
     * {@link FieldContext#getFieldType()} was found, {@code toString()} will be
     * used.
     * 
     * @param writer
     * @param fieldContext
     */
    protected void writeValueAsString(IndentedWriter writer, FieldContext fieldContext)
    {
        writer.write("String %s = null;", fieldContext.getValueAsStringVariable());
        String converterVariable = fieldContext.newVariableName("WriteConverter");
        writer.write("Converter<%1$s> %2$s = converterRegistry.get(%1$s.class);", fieldContext.getFieldType()
                .getQualifiedSourceName(), converterVariable);
        writer.write("if (%s != null) {", converterVariable);
        // Use the registered converter
        writer.indent();
        if (fieldContext.getFormat() != null)
        {
            writer.write("%s = %s.serialize(%s, \"%s\");", fieldContext.getValueAsStringVariable(), converterVariable,
                    fieldContext.getValueVariable(), fieldContext.getFormat());
        }
        else
        {
            writer.write("%s = %s.serialize(%s, null);", fieldContext.getValueAsStringVariable(), converterVariable,
                    fieldContext.getValueVariable());
        }
        writer.outdent();
        writer.write("}");
        writer.write("else {");
        // Fall back to toString()
        writer.indent();
        writer.write("%s = String.valueOf(%s);", fieldContext.getValueAsStringVariable(),
                fieldContext.getValueVariable());
        writer.outdent();
        writer.write("}");
    }
}
