package name.pehl.piriti.client.gwttest.converter;

import name.pehl.piriti.client.converter.Converter;

public class DurationConverter implements Converter<Double>
{
    @Override
    public Double convert(String value, String format)
    {
        if (value != null && value.length() > 0)
        {
            return Double.valueOf(value.substring(0, value.length() - 1));
        }
        return 0d;
    }


    @Override
    public String serialize(Double value, String format)
    {
        return value + "h";
    }
}
