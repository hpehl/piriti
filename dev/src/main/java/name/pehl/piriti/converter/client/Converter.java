package name.pehl.piriti.converter.client;

/**
 * Simple converter to convert strings into the specified type T and vice versa.
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
     *            The string to be converted. May be <code>null</code>.
     * @return The converted type or <code>null</code> if a conversion is not
     *         possible
     */
    T convert(String value);


    /**
     * Serializes the specified value to a string.
     * 
     * @param value
     *            The value to serialize. May be <code>null</code>.
     * @return The serialized string or <code>null</code> if no value was given.
     */
    String serialize(T value);
}
