package name.pehl.piriti.commons.client;

/**
 * Common super interface for JSON and XML readers
 * 
 * @param <T>
 *            The type
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 46 $
 */
public interface Reader<T>
{
    /**
     * Returns the reference for the specified identifier or <code>null</code>
     * if no reference was found.
     * 
     * @param id
     * @return
     */
    T idRef(String id);
}
