package name.pehl.piriti.converter.client;

/**
 * Simple converter to convert strings into the specified type T and vice versa.
 * <p>
 * <strong>Please note:</strong> Implementations of this interface must provide
 * a default / no-arg constructor, since they are created with
 * {@link com.google.gwt.core.client.GWT#create(Class)}.
 * 
 * @param <T>
 *            The target type for this converter.
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 7 $
 */
public interface Converter<T>
{
    /**
     * Method to set a format for the converter. The format must be set before
     * the first call to {@link #convert(String)} or {@link #serialize(Object)}.
     * 
     * @param format
     */
    void setFormat(String format);


    String getFormat();


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
