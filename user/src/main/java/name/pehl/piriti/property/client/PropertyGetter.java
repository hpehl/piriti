package name.pehl.piriti.property.client;

/**
 * Interface for getting the property P in T.
 * 
 * @param <T>
 *            The model
 * @param <P>
 *            The property
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public interface PropertyGetter<T, P>
{
    /**
     * Reads the property P from T.
     * 
     * @param model
     *            the model instance.
     * @return the result of type P.
     */
    P get(T model);
}
