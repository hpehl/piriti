package name.pehl.piriti.commons.client;

/**
 * Interface to create new instances of T from information provided by C.
 * 
 * @param <T>
 *            the model type
 * @param <C>
 *            the context type
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public interface InstanceCreator<T, C>
{
    T newInstance(C context);
}
