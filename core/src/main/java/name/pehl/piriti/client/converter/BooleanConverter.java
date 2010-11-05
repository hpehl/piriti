package name.pehl.piriti.client.converter;


/**
 * Converter for boolean objects. Uses {@code Boolean.valueOf(value)} for the
 * conversion.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 7 $
 */
public class BooleanConverter extends AbstractConverter<Boolean>
{
    /**
     * Converts the specified value to boolean.
     * 
     * @param value
     *            The string to be converted. May be <code>null</code>
     * @param format
     *            Ignored
     * @return {@code null} if the value is {@code null} or empty, otherwise
     *         {@code Boolean.valueOf(value)}
     * @see name.pehl.piriti.client.converter.Converter#convert(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Boolean convert(String value, String format)
    {
        if (isValid(value))
        {
            return Boolean.valueOf(value);
        }
        return null;
    }
}
