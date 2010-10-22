package name.pehl.piriti.client.property;

/**
 * Interface for setting the property P in T.
 * 
 * @param <T>
 *            The model
 * @param <P>
 *            The property
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public interface PropertySetter<T, P>
{
    /**
     * Sets the property P in T.
     * 
     * @param model
     *            the model instance
     * @param value
     *            the value to set
     */
    void set(T model, P value);
}
