package name.pehl.piriti.property.client;

/**
 * Noop {@link Setter} used as default value in annotations.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class NoopPropertySetter implements PropertySetter<Object, Object>
{

    /**
     * Throws an {@link UnsupportedOperationException}.
     * 
     * @param model
     * @param value
     * @see name.pehl.piriti.property.client.PropertySetter#set(java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public void set(Object model, Object value)
    {
        throw new UnsupportedOperationException("Not implemented");
    }
}
