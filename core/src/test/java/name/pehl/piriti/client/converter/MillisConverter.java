package name.pehl.piriti.client.converter;

import java.util.Date;

import name.pehl.piriti.converter.client.AbstractConverter;

public class MillisConverter extends AbstractConverter<Date>
{
    @Override
    public Date convert(String value)
    {
        Date date = null;
        if (isValid(value))
        {
            long millis = Long.parseLong(value);
            date = new Date(millis);
        }
        return date;
    }


    @Override
    public String serialize(Date value)
    {
        return String.valueOf(value.getTime());
    }

}
