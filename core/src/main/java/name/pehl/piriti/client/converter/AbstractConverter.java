package name.pehl.piriti.client.converter;

import com.google.inject.internal.Nullable;

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
     * @return {@code true} if the value is not {@code null} or empty, {@code
     *         false} otherwise
     */
    protected boolean isValid(@Nullable String value)
    {
        return value != null && value.trim().length() != 0;
    }
}
