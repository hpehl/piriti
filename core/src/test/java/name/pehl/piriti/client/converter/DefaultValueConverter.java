package name.pehl.piriti.client.converter;

import name.pehl.piriti.converter.client.Converter;

public class DefaultValueConverter implements Converter<Integer>
{
    @Override
    public Integer convert(String value, String format)
    {
        return 0;
    }


    @Override
    public String serialize(Integer value, String format)
    {
        return "0";
    }
}
