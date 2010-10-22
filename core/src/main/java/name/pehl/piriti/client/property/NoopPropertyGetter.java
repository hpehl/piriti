package name.pehl.piriti.client.property;

/**
 * Noop converter used as default value in
 * {@link name.pehl.piriti.client.json.Json#converter()} and
 * {@link name.pehl.piriti.client.xml.Xml#converter()}.
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
     * @see name.pehl.piriti.client.property.PropertyGetter#get(java.lang.Object)
     */
    @Override
    public Object get(Object model)
    {
        throw new UnsupportedOperationException("Not implemented");
    }
}
