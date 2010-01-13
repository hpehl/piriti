package name.pehl.gwt.piriti.rebind;

import com.google.gwt.core.client.GWT;

/**
 * Base class for all {@linkplain FieldHandler}s which contains common code.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public abstract class AbstractFieldHandler implements FieldHandler
{
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
