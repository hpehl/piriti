package name.pehl.gwt.piriti.rebind.fieldhandler;

import name.pehl.gwt.piriti.rebind.FieldContext;
import name.pehl.gwt.piriti.rebind.IndentedWriter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Base class for all {@linkplain FieldHandler}s which contains common code.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
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
