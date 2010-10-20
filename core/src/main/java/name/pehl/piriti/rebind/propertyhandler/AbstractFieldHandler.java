package name.pehl.piriti.rebind.propertyhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Abstract base class for {@linkplain FieldHandler}s which contains common
 * code. Contains default implementations for all methods but
 * {@link #readInput(IndentedWriter, FieldContext)}.
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
    public void comment(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("// %s: %s", getClass().getSimpleName(), fieldContext);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void declare(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("%s %s = null;", fieldContext.getFieldType().getParameterizedQualifiedSourceName(),
                fieldContext.getValueVariable());
    }


    /**
     * {@inheritDoc}
     * <p>
     * Delegates to {@link CodeGeneration#assign(IndentedWriter, FieldContext)}.
     * The assignement is only done if {@link FieldContext#getValueVariable()}
     * is not null.
     */
    @Override
    public void assign(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        CodeGeneration.assign(writer, fieldContext);
    }


    /**
     * {@inheritDoc}
     * <p>
     * Delegates to
     * {@link CodeGeneration#readField(IndentedWriter, FieldContext)}.
     */
    @Override
    public void readField(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        CodeGeneration.readField(writer, fieldContext);
    }
}
