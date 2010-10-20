package name.pehl.piriti.rebind.propertyhandler;

import name.pehl.piriti.client.converter.Converter;
import name.pehl.piriti.client.converter.ConverterRegistry;
import name.pehl.piriti.rebind.IndentedWriter;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * {@link PropertyHandler} implementation which uses a {@link Converter} from the
 * {@link ConverterRegistry}.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public abstract class AbstractConverterPropertyHandler extends AbstractPropertyHandler
{
    /**
     * Returns always <code>true</code>.
     * 
     * @param writer
     * @param fieldContext
     * @return always <code>true</code>
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler#isValid(name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        return true;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void readInput(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
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
     * {@link PropertyContext#getValueAsStringVariable()}.
     * 
     * @param writer
     * @param fieldContext
     */
    protected abstract void readInputAsString(IndentedWriter writer, PropertyContext fieldContext);


    /**
     * Converts the {@link PropertyContext#getValueVariable()} to a String
     * represented by {@link PropertyContext#getValueAsStringVariable()} using a
     * registered converter. If no converter for
     * {@link PropertyContext#getFieldType()} was found, {@code toString()} will be
     * used.
     * 
     * @param writer
     * @param fieldContext
     */
    protected void writeValueAsString(IndentedWriter writer, PropertyContext fieldContext)
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
