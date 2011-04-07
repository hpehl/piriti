package name.pehl.piriti.client.inheritance;

import name.pehl.piriti.converter.client.Converter;

/**
 * @author $Author$
 * @version $Date$ $Revision:
 *          1478 $
 */
public class SizeConverter implements Converter<Size>
{
    @Override
    public Size convert(String value, String format)
    {
        if ("S".equalsIgnoreCase(value))
        {
            return Size.TINY;
        }
        else if ("M".equalsIgnoreCase(value))
        {
            return Size.NORMAL;
        }
        else if ("L".equalsIgnoreCase(value))
        {
            return Size.HUGE;
        }
        else
        {
            return null;
        }
    }


    @Override
    public String serialize(Size value, String format)
    {
        String result = null;
        switch (value)
        {
            case TINY:
                result = "S";
                break;
            case NORMAL:
                result = "M";
                break;
            case HUGE:
                result = "L";
                break;
            default:
                break;
        }
        return result;
    }
}
