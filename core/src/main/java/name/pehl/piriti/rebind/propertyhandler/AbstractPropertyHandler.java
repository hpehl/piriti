package name.pehl.piriti.rebind.propertyhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Abstract base class for {@linkplain PropertyHandler}s which contains common
 * code. Contains default implementations for all methods but
 * {@link #readInput(IndentedWriter, PropertyContext)}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public abstract class AbstractPropertyHandler implements PropertyHandler
{
    /**
     * {@inheritDoc}
     */
    @Override
    public void comment(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("// %s: %s", getClass().getSimpleName(), propertyContext);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void declare(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("%s %s = null;", propertyContext.getType().getParameterizedQualifiedSourceName(), propertyContext
                .getVariableNames().getValueVariable());
    }


    /**
     * {@inheritDoc}
     * <p>
     * Delegates to
     * {@link CodeGeneration#assign(IndentedWriter, PropertyContext)}. The
     * assignement is only done if {@link PropertyContext#getValueVariable()} is
     * not null.
     */
    @Override
    public void assign(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        CodeGeneration.assign(writer, propertyContext);
    }


    /**
     * {@inheritDoc}
     * <p>
     * Delegates to
     * {@link CodeGeneration#readField(IndentedWriter, PropertyContext)}.
     */
    @Override
    public void readProperty(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        CodeGeneration.readField(writer, propertyContext);
    }
}
