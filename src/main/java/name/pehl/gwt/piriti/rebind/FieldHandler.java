package name.pehl.gwt.piriti.rebind;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Interface for generating code for one field assignment.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public interface FieldHandler
{
    /**
     * Checks whether the handler can process the field. Returning
     * <code>false</code> skips the field during code generation.
     * 
     * @param writer
     *            Can be used to write a fail comment
     * @param fieldContext
     * @return
     */
    boolean isValid(IndentedWriter writer, FieldContext fieldContext);


    /**
     * Generates code for one field assignment.
     * 
     * @param writer
     * @param fieldContext
     */
    void write(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException;

}
