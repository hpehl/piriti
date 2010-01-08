package name.pehl.gwt.piriti.client.converter;

/**
 * Base class for all converters with some common functionality.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision$
 */
public abstract class AbstractConverter<T> implements Converter<T>
{
    /**
     * Checks whether the value is valid.
     * 
     * @param value
     *            The value to check
     * @return {@code true} if the value is not {@code null} or empty, {@code
     *         false} otherwise
     */
    protected boolean isValid(String value)
    {
        return value != null && value.trim().length() != 0;
    }
}
