package name.pehl.gwt.piriti.client.converter;

/**
 * @author $Author:$
 * @version $Revision:$
 */
public class CharacterConverter implements Converter<Character>
{
    @Override
    public Character convert(String value, String format)
    {
        if (value == null)
        {
            return null;
        }
        try
        {
            return value.charAt(0);
        }
        catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }
}
