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
    public void writeConverterCode(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writeReadValueAsString(writer, fieldContext);
        writer.write("if (%s != null) {", fieldContext.newVariableName("AsString"));
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
    protected abstract void writeReadValueAsString(IndentedWriter writer, FieldContext fieldContext);
}
