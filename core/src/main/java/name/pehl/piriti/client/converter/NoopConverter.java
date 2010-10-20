package name.pehl.piriti.client.converter;

/**
 * Noop converter used as default value in
 * {@link name.pehl.piriti.client.json.JsonField#converter()} and
 * {@link name.pehl.piriti.client.xml.XmlField#converter()}.
 * 
 * @author $Author$
 * @version $Date$ $Revision:
 *          1097 $
 */
@SuppressWarnings("rawtypes")
public class NoopConverter implements Converter
{

    /**
     * Throws an {@link UnsupportedOperationException}.
     * 
     * @param value
     * @param format
     * @return
     * @see name.pehl.piriti.client.converter.Converter#convert(java.lang.String,
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
     * @see name.pehl.piriti.client.converter.Converter#serialize(java.lang.Object,
     *      java.lang.String)
     */
    @Override
    public String serialize(Object value, String format)
    {
        throw new UnsupportedOperationException("Not implemented");
    }
}
