package name.pehl.piriti.rebind;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Interface for processing a {@link TypeContext}. Applies the
 * chain-of-responsibility pattern. After the last processor has finished, all
 * properties must be resolved (references and an id are optional).
 * <p>
 * Implementations can choose which information they add to the
 * {@link TypeContext} and hand over to the next processor to add further info.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public interface TypeProcessor
{
    /**
     * Processes the context and adds information about properties, references
     * or an id.
     * 
     * @param context
     */
    void process(TypeContext context) throws UnableToCompleteException;


    /**
     * Sets the next processor
     * 
     * @param processor
     */
    void setNext(TypeProcessor processor);


    /**
     * @return <code>true</code> if there's a next processor, <code>false</code>
     *         otherwise.
     */
    boolean hasNext();
}
