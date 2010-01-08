package name.pehl.gwt.piriti.rebind;

/**
 * Interface for generating code for one field assignment.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public interface FieldHandler
{
    /**
     * Generates code for one field assignment.
     * 
     * @param writer
     * @param fieldContext
     */
    void write(IndentedWriter writer, FieldContext fieldContext);
}
