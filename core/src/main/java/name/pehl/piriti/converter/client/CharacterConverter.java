package name.pehl.piriti.converter.client;

/**
 * Converter for character objects. Uses the first character of the value for
 * the conversion.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 7 $
 */
public class CharacterConverter extends AbstractConverter<Character>
{
    /**
     * Converts the specified value to boolean.
     * 
     * @param value
     *            The string to be converted. May be <code>null</code>.
     * @return {@code null} if the value is {@code null}, otherwise the first
     *         character of the value
     * @see name.pehl.piriti.converter.client.Converter#convert(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Character convert(String value)
    {
        if (value != null && value.length() > 0)
        {
            return value.charAt(0);
        }
        return null;
    }


    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return new CharacterConverter();
    }
}
