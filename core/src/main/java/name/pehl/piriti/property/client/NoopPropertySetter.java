package name.pehl.piriti.property.client;

/**
 * Noop converter used as default value in
 * {@link name.pehl.piriti.json.client.Json#converter()} and
 * {@link name.pehl.piriti.xml.client.Xml#converter()}.
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
