package name.pehl.gwt.piriti.client.converter;

/**
 * Converter for character objects. Uses the first character of the value for
 * the conversion.
 * 
 * @author $Author$
 * @version $Revision$
 */
public class CharacterConverter extends AbstractConverter<Character>
{
    /**
     * Converts the specified value to boolean.
     * 
     * @param value
     *            The string to be converted
     * @param format
     *            Ignored
     * @return {@code null} if the value is {@code null}, otherwise the first
     *         character of the value
     * @see name.pehl.gwt.piriti.client.converter.Converter#convert(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Character convert(String value, String format)
    {
        if (value != null && value.length() > 0)
        {
            return value.charAt(0);
        }
        return null;
    }
}
