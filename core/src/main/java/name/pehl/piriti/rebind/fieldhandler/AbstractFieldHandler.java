package name.pehl.piriti.rebind.fieldhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Abstract base class for {@linkplain FieldHandler}s which contains common
 * code. Contains default implementations for all methods but
 * {@link #writeConverterCode(IndentedWriter, FieldContext)}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public abstract class AbstractFieldHandler implements FieldHandler
{
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
        writer.write("%s %s = null;", fieldContext.getFieldType().getParameterizedQualifiedSourceName(),
                fieldContext.getValueVariable());
    }


    /**
     * {@inheritDoc}
     * <p>
     * The assignement is only done if {@link FieldContext#getValueVariable()}
     * is not null.
     */
    @Override
    public void writeAssignment(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        CodeGeneration.assign(writer, fieldContext);
    }


    /**
     * NYI
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.fieldhandler.FieldHandler#writeSerialization(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.fieldhandler.FieldContext)
     */
    @Override
    public void writeSerialization(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("// Not yet implemented!");
    }
}
