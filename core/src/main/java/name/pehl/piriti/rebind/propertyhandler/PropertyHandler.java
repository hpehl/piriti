package name.pehl.piriti.rebind.propertyhandler;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Interface for generating code for one property.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public interface PropertyHandler
{
    // -------------------------------------- methods used in reader and writer

    /**
     * Checks whether the handler can process the property. Returning
     * <code>false</code> skips the property during code generation.
     * 
     * @param writer
     *            Can be used to write a fail comment
     * @param propertyContext
     * @return
     */
    boolean isValid(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException;


    /**
     * Generated a comment containing the properties name, type and the relevant
     * path
     * 
     * @param writer
     * @param propertyContext
     */
    void comment(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException;


    /**
     * Generates the variable declaration of
     * {@link PropertyContext#getValueVariable()}. The variable is used in the
     * remaining methods. The type of the variable will be
     * <code>fieldContext.getFieldType().getParameterizedQualifiedSourceName()</code>
     * .
     * 
     * @param writer
     * @param propertyContext
     */
    void declare(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException;


    // ------------------------------------------------- methods used in reader

    /**
     * Generates the code for reading the input (JSON / XML), convert if
     * necessary and assign it to {@link PropertyContext#getValueVariable()}.
     * 
     * @param writer
     * @param propertyContext
     * @param propertyHandlerRegistry
     */
    void readInput(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerRegistry propertyHandlerRegistry) throws UnableToCompleteException;


    /**
     * Generates the assignment of {@link PropertyContext#getValueVariable()} to
     * the property. The assignment should only be done when the
     * {@link PropertyContext#getValueVariable()} <code>!= null</code>.
     * 
     * @param writer
     * @param propertyContext
     */
    void assign(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException;


    // ------------------------------------------------- methods used in writer

    /**
     * Generates code to read the properties value and assign it to
     * {@link PropertyContext#getValueVariable()}.
     * 
     * @param writer
     * @param propertyContext
     * @throws UnableToCompleteException
     */
    void readProperty(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException;


    /**
     * Generates code which is neccessary <i>before</i> the {@code toXyz()}
     * method is called.
     * 
     * @param writer
     * @param propertyContext
     * @throws UnableToCompleteException
     */
    void markupStart(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException;


    /**
     * Generates code for the {@code toXyz()} method.
     * 
     * @param writer
     * @param propertyContext
     * @param propertyHandlerRegistry
     * @throws UnableToCompleteException
     */
    void writeValue(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerRegistry propertyHandlerRegistry) throws UnableToCompleteException;


    /**
     * Generates code which is neccessary <i>after</i> the {@code toXyz()}
     * method was called.
     * 
     * @param writer
     * @param propertyContext
     * @throws UnableToCompleteException
     */
    void markupEnd(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException;
}
