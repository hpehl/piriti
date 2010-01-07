package name.pehl.gwt.piriti.client.converter;

/**
 * @author $Author:$
 * @version $Revision:$
 */
public class ShortConverter implements Converter<Short>
{
    @Override
    public Short convert(String value, String format)
    {
        if (value == null || value.trim().length() == 0)
        {
            return null;
        }
        try
        {
            return Short.valueOf(value);
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }
}
