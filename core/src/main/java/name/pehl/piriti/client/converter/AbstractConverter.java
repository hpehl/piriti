package name.pehl.piriti.client.converter;


/**
 * Base class for all converters with some common functionality.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 6 $
 */
public abstract class AbstractConverter<T> implements Converter<T>
{
    /**
     * Checks whether the value is valid.
     * 
     * @param value
     *            The value to check. May be <code>null</code>.
     * @return {@code true} if the value is not {@code null} or empty,
     *         {@code false} otherwise
     */
    protected boolean isValid(String value)
    {
        return value != null && value.trim().length() != 0;
    }


    /**
     * Implementation based on {@link String#valueOf(Object)}.
     * 
     * @param value
     * @param format
     * @return
     * @see name.pehl.piriti.client.converter.Converter#serialize(java.lang.Object,
     *      java.lang.String)
     */
    @Override
    public String serialize(T value, String format)
    {
        if (value != null)
        {
            return String.valueOf(value);
        }
        return null;
    }
}
