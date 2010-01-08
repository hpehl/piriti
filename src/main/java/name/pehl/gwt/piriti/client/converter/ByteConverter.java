package name.pehl.gwt.piriti.client.converter;

/**
 * Converter for byte objects. Uses {@code Byte.valueOf(value)} for the
 * conversion.
 * 
 * @author $Author$
 * @version $Revision$
 */
public class ByteConverter extends AbstractConverter<Byte>
{
    /**
     * Converts the specified value to byte.
     * 
     * @param value
     *            The string to be converted
     * @param format
     *            Ignored
     * @return {@code null} if the value is {@code null}, empty or in the wrong
     *         format, otherwise {@code Byte.valueOf(value)}
     * @see name.pehl.gwt.piriti.client.converter.Converter#convert(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Byte convert(String value, String format)
    {
        if (isValid(value))
        {
            try
            {
                return Byte.valueOf(value);
            }
            catch (NumberFormatException e)
            {
                return null;
            }
        }
        return null;
    }
}
