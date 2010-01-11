package name.pehl.gwt.piriti.rebind;

/**
 * Interface for generating code for one field assignment.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public interface FieldHandler
{
    /**
     * Checks whether the field is valid. Fields are invalid if one of the following is true:
     * <ul>
     *  <li>The type of the field euqals the model type
     *  <li>If the field is an array: The coponent type of the array euqals the model type
     *  <li>If the field is a collection or map:
     *  <ul>
     *      <li>The collection or map does not have type arguments
     *      <li>One of the type arguments equals the model type
     *  </ul>  
     * </ul>
     * @param fieldContext
     * @return
     */
    boolean isValid(FieldContext fieldContext);
    
    /**
     * Generates code for one field assignment.
     * 
     * @param writer
     * @param fieldContext
     */
    void write(IndentedWriter writer, FieldContext fieldContext);

}
