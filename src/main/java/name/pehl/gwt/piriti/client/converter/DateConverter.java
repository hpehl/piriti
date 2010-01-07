package name.pehl.gwt.piriti.client.converter;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * @author $Author:$
 * @version $Revision:$
 */
public class DateConverter implements Converter<Date>
{
    public static final String DEFAULT_FORMAT = "dd.MM.yyyy HH:mm:ss.SSS";


    @Override
    public Date convert(String value, String format)
    {
        if (value == null || value.trim().length() == 0)
        {
            return null;
        }
        return parseDate(value, format == null ? DEFAULT_FORMAT : format);
    }


    /**
     * Extra method for better unit testing
     * 
     * @param value
     * @param format
     * @return
     */
    protected Date parseDate(String value, String format)
    {
        DateTimeFormat dtFormat = DateTimeFormat.getFormat(format);
        try
        {
            return dtFormat.parse(value);
        }
        catch (IllegalArgumentException e)
        {
            return null;
        }
    }
}
