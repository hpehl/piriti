package name.pehl.piriti.client.converter;

/**
 * Simple converter to convert strings into the specified type T.
 * 
 * @param <T>
 *            The target type for this converter.
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 7 $
 */
public interface Converter<T>
{
    /**
     * Converts the specified value to the type T.
     * 
     * @param value
     *            The string to be converted
     * @param format
     *            An optional format of the value (eg for dates). Should be
     *            {@code null} if no format is supported
     * @return The converted type or <code>null</code> if a conversion is not
     *         possible
     */
    T convert(String value, String format);
}
