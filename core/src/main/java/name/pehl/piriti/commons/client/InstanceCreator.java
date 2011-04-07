package name.pehl.piriti.commons.client;

/**
 * Interface to create new instances of T from information provided by C.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public interface InstanceCreator<T, C>
{
    T newInstance(C context);
}
