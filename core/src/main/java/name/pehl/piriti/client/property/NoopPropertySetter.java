package name.pehl.piriti.client.property;

/**
 * Noop converter used as default value in
 * {@link name.pehl.piriti.client.json.Json#converter()} and
 * {@link name.pehl.piriti.client.xml.Xml#converter()}.
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
     * @see name.pehl.piriti.client.property.PropertySetter#set(java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public void set(Object model, Object value)
    {
        throw new UnsupportedOperationException("Not implemented");
    }
}
