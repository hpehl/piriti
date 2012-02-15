package name.pehl.piriti.client.converter;

import name.pehl.piriti.converter.client.AbstractConverter;

public class DurationConverter extends AbstractConverter<Integer>
{
    @Override
    public Integer convert(String value)
    {
        if (isValid(value))
        {
            return Integer.valueOf(value.substring(0, value.length() - 1));
        }
        return 0;
    }


    @Override
    public String serialize(Integer value)
    {
        return value + "h";
    }
}
