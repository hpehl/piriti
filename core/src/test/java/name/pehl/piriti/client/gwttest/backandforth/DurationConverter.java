package name.pehl.piriti.client.gwttest.backandforth;

import name.pehl.piriti.client.converter.Converter;

public class DurationConverter implements Converter<Integer>
{
    @Override
    public Integer convert(String value, String format)
    {
        if (value != null && value.length() > 0)
        {
            return Integer.valueOf(value.substring(0, value.length() - 1));
        }
        return 0;
    }


    @Override
    public String serialize(Integer value, String format)
    {
        return value + "h";
    }
}
