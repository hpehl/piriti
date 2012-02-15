package name.pehl.piriti.property.client;

/**
 * Noop {@link Getter} used as default value in annotations.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class NoopPropertyGetter implements PropertyGetter<Object, Object>
{
    /**
     * Throws an {@link UnsupportedOperationException}.
     * 
     * @param model
     * @return
     * @see name.pehl.piriti.property.client.PropertyGetter#get(java.lang.Object)
     */
    @Override
    public Object get(Object model)
    {
        throw new UnsupportedOperationException("Not implemented");
    }
}
