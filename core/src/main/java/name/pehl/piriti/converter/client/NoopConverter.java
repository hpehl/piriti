package name.pehl.piriti.converter.client;

/**
 * Noop converter used as default value in
 * {@link name.pehl.piriti.json.client.Json#converter()} and
 * {@link name.pehl.piriti.xml.client.Xml#converter()}.
 * 
 * @author $Author$
 * @version $Date$ $Revision:
 *          1097 $
 */
public class NoopConverter implements Converter<Object>
{

    /**
     * Throws an {@link UnsupportedOperationException}.
     * 
     * @param value
     * @param format
     * @return
     * @see name.pehl.piriti.converter.client.Converter#convert(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Object convert(String value, String format)
    {
        throw new UnsupportedOperationException("Not implemented");
    }


    /**
     * Throws an {@link UnsupportedOperationException}.
     * 
     * @param value
     * @param format
     * @return
     * @see name.pehl.piriti.converter.client.Converter#serialize(java.lang.Object,
     *      java.lang.String)
     */
    @Override
    public String serialize(Object value, String format)
    {
        throw new UnsupportedOperationException("Not implemented");
    }
}
