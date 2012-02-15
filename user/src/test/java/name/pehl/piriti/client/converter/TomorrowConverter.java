package name.pehl.piriti.client.converter;

import java.util.Date;

import name.pehl.piriti.converter.client.AbstractConverter;

@SuppressWarnings("deprecation")
public class TomorrowConverter extends AbstractConverter<Date>
{
    public final static String TOMORROW_NAME = "tomorrow";
    public final static Date TOMORROW_DATE = new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000));
    static
    {
        TOMORROW_DATE.setHours(0);
        TOMORROW_DATE.setMinutes(0);
        TOMORROW_DATE.setSeconds(0);
    }


    @Override
    public Date convert(String value)
    {
        if (TOMORROW_NAME.equals(value))
        {
            return TOMORROW_DATE;
        }
        return null;
    }


    @Override
    public String serialize(Date value)
    {
        return TOMORROW_NAME;
    }
}
