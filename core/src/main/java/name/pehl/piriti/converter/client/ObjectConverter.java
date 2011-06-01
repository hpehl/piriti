package name.pehl.piriti.converter.client;

/**
 * Converter for objects.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 7 $
 */
public class ObjectConverter extends AbstractConverter<Object>
{
    /**
     * Returns the specified as Object.
     * 
     * @param value
     *            The string to be converted. May be <code>null</code>
     * @param format
     *            Ignored
     * @return {@code null} if the value is {@code null} or empty, otherwise the
     *         value itself
     * @see name.pehl.piriti.converter.client.Converter#convert(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Object convert(String value, String format)
    {
        if (isValid(value))
        {
            return value;
        }
        return null;
    }
}
