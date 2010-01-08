package name.pehl.gwt.piriti.client.converter;

/**
 * Converter for boolean objects. Uses {@code Boolean.valueOf(value)} for the
 * conversion.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class BooleanConverter extends AbstractConverter<Boolean>
{
    /**
     * Converts the specified value to boolean.
     * 
     * @param value
     *            The string to be converted
     * @param format
     *            Ignored
     * @return {@code null} if the value is {@code null} or empty, otherwise
     *         {@code Boolean.valueOf(value)}
     * @see name.pehl.gwt.piriti.client.converter.Converter#convert(java.lang.String,
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
