package name.pehl.gwt.piriti.client.converter;

/**
 * @author $Author:$
 * @version $Revision:$
 */
public class BooleanConverter implements Converter<Boolean>
{
    @Override
    public Boolean convert(String value, String format)
    {
        if (value == null || value.trim().length() == 0)
        {
            return null;
        }
        return Boolean.valueOf(value);
    }
}
