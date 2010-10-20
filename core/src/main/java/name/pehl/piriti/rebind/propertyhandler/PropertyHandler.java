package name.pehl.piriti.rebind.propertyhandler;

import name.pehl.piriti.rebind.IndentedWriter;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Interface for generating code for one field.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public interface PropertyHandler
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
    boolean isValid(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException;


    /**
     * Generated a comment containing the fields name, type and the relevant
     * path
     * 
     * @param writer
     * @param fieldContext
     */
    void comment(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException;


    /**
     * Generates the variable declaration of
     * {@link PropertyContext#getValueVariable()}. The variable is used in the
     * remaining methods. The type of the variable will be
     * <code>fieldContext.getFieldType().getParameterizedQualifiedSourceName()</code>
     * .
     * 
     * @param writer
     * @param fieldContext
     */
    void declare(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException;


    /**
     * Generates the code for reading the input (JSON / XML), convert if and
     * assign it to {@link PropertyContext#getValueVariable()}.
     * 
     * @param writer
     * @param fieldContext
     */
    void readInput(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException;


    /**
     * Generates the assignment of {@link PropertyContext#getValueVariable()} to
     * the the field. The assignment should only be done when the
     * {@link PropertyContext#getValueVariable()} <code>!= null</code>.
     * 
     * @param writer
     * @param fieldContext
     */
    void assign(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException;


    /**
     * Generates code to read the fields value and assign it to
     * {@link PropertyContext#getValueVariable()}.
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     */
    void readField(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException;


    /**
     * Generates code which is neccessary <i>before</i> the {@code toXyz()}
     * method is called.
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     */
    void markupStart(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException;


    /**
     * Generates code for the {@code toXyz()} method.
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     */
    void writeValue(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException;


    /**
     * Generates code which is neccessary <i>after</i> the {@code toXyz()}
     * method was called.
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     */
    void markupEnd(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException;
}
