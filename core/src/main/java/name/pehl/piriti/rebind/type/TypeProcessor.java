package name.pehl.piriti.rebind.type;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Interface for processing and populating a {@link TypeContext}. Applies the
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
     * <p>
     * TODO All PropertyContext instances must be complete for rendering after
     * this method returns. Nested contexts must be created, all template paths
     * must be set!
     * 
     * @param context
     */
    void process(TypeContext context) throws UnableToCompleteException;


    TypeProcessor getNext();


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
