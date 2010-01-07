package name.pehl.gwt.piriti.client.converter;

/**
 * @author $Author:$
 * @version $Revision:$
 */
public class ByteConverter implements Converter<Byte>
{
    @Override
    public Byte convert(String value, String format)
    {
        if (value == null || value.trim().length() == 0)
        {
            return null;
        }
        try
        {
            return Byte.valueOf(value);
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }
}
