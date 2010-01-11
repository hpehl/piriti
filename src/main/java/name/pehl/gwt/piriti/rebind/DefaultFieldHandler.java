package name.pehl.gwt.piriti.rebind;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Default implementation for a {@link FieldHandler}. This class calls the
 * following methods in this order:
 * <ol>
 * <li>{@link #writeComment(IndentedWriter, FieldContext)}
 * <li>{@link #writeDeclaration(IndentedWriter, FieldContext)}
 * <li>{@link #writeConverterCode(IndentedWriter, FieldContext)}
 * <li>{@link #writeAssignment(IndentedWriter, FieldContext)}
 * </ol>
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
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext)
    {
        return true;
    }


    @Override
    public void write(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writeComment(writer, fieldContext);
        writeDeclaration(writer, fieldContext);
        writeConverterCode(writer, fieldContext);
        writeAssignment(writer, fieldContext);
    }
}
