package name.pehl.piriti.client.gwttest.backandforth;

import name.pehl.piriti.client.converter.Converter;

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
