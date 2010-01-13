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
    boolean isValid(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException;


    /**
     * Generated a comment for the field assignement containing the fields name,
     * type and the relevant xpath
     * 
     * @param writer
     * @param fieldContext
     */
    void writeComment(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException;


    /**
     * Generates the variable decleration for field assignment.
     * 
     * @param writer
     * @param fieldContext
     */
    void writeDeclaration(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException;


    /**
     * Generates the code for converting the string read from XML to the fields
     * type.
     * 
     * @param writer
     * @param fieldContext
     */
    void writeConverterCode(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException;


    /**
     * Generates the assignment for the field. The assignment is only done when
     * the xpath expression returns valid data (!= null).
     * 
     * @param writer
     * @param fieldContext
     */
    void writeAssignment(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException;
}
