package name.pehl.gwt.piriti.client.converter;

/**
 * Registry for converter.
 * 
 * @author $Author$
 * @version $Revision$
 */
public interface ConverterRegistry
{
    /**
     * Registers the specified converter for the specified class.
     * 
     * @param <T>
     *            The class of the converter
     * @param clazz
     *            The class
     * @param converter
     *            The converter
     */
    <T> void register(Class<T> clazz, Converter<T> converter);


    /**
     * Returns a converter for the specified type.
     * 
     * @param <T>
     *            The converter type
     * @param clazz
     *            The class for the type
     * @return A converter for the type T or {@code null} if no converter was
     *         found
     */
    <T> Converter<T> get(Class<T> clazz);
}
