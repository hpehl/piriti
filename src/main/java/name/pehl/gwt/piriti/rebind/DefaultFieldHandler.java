package name.pehl.gwt.piriti.rebind;

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
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class DefaultFieldHandler extends AbstractFieldHandler
{
    @Override
    public void write(IndentedWriter writer, FieldContext fieldContext)
    {
        writeComment(writer, fieldContext);
        writeDeclaration(writer, fieldContext);
        writeConverterCode(writer, fieldContext);
        writeAssignment(writer, fieldContext);
    }
}
