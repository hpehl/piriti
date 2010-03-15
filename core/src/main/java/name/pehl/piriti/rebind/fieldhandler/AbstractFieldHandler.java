package name.pehl.piriti.rebind.fieldhandler;

import name.pehl.piriti.rebind.FieldContext;
import name.pehl.piriti.rebind.IndentedWriter;

import com.google.gwt.core.client.GWT;
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
        writer.write("%s %s = null;", fieldContext.getFieldType().getParameterizedQualifiedSourceName(), fieldContext
                .getValueVariable());
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
        writer.write("if (%s != null) {", fieldContext.getValueVariable());
        writer.indent();
        writer.write("model.%s = %s;", fieldContext.getFieldName(), fieldContext.getValueVariable());
        writer.outdent();
        writer.write("}");
    }


    /**
     * Generates code comments if a field was skipped (contains the reason why
     * the field was skipped)
     * 
     * @param writer
     * @param fieldContext
     * @param reason
     */
    protected void skipField(IndentedWriter writer, FieldContext fieldContext, String reason)
    {
        writer.write("// Skipping field %s", fieldContext);
        writer.write("// " + reason);
        GWT.log("Skipping field " + fieldContext, null);
        GWT.log(reason, null);
    }
}
