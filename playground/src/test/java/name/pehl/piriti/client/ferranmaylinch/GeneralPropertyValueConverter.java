package name.pehl.piriti.client.ferranmaylinch;

import java.util.Date;

import name.pehl.piriti.converter.client.Converter;

import com.google.gwt.i18n.client.DateTimeFormat;



public class GeneralPropertyValueConverter implements Converter<Object>
{
    @Override
    public Object convert(String value, String format)
    {
        Object result = value;
        // first try integer
        try
        {
            result = Integer.parseInt(value);
        }
        catch (NumberFormatException nfe)
        {
            try
            {
                result = DateTimeFormat.getFormat(Constants.DATE_FORMAT).parse(value);
            }
            catch (IllegalArgumentException iae)
            {
                // value is neither an integer nor a date --> return as
                // string
            }
        }
        return result;
    }


    @Override
    public String serialize(Object value, String format)
    {
        String result = String.valueOf(value);
        if (value instanceof Date)
        {
            result = DateTimeFormat.getFormat(Constants.DATE_FORMAT).format((Date) value);
        }
        return result;
    }
}