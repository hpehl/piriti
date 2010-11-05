package name.pehl.piriti.client.converter;


/**
 * Converter for short objects. Uses {@code Short.valueOf(value)} for the
 * conversion.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 7 $
 */
public class ShortConverter extends AbstractConverter<Short>
{
    /**
     * Converts the specified value to short.
     * 
     * @param value
     *            The string to be converted. May be <code>null</code>.
     * @param format
     *            Ignored
     * @return {@code null} if the value is {@code null} or empty, otherwise the
     *         converted short
     * @see name.pehl.piriti.client.converter.Converter#convert(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Short convert(String value, String format)
    {
        if (isValid(value))
        {
            try
            {
                return Short.valueOf(value);
            }
            catch (NumberFormatException e)
            {
                return null;
            }
        }
        return null;
    }
}
