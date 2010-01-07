package name.pehl.gwt.piriti.client.converter;

import com.google.gwt.i18n.client.NumberFormat;

/**
 * @author $Author:$
 * @version $Revision:$
 */
public class LongConverter implements Converter<Long>
{
    @Override
    public Long convert(String value, String format)
    {
        if (value == null || value.trim().length() == 0)
        {
            return null;
        }
        try
        {
            if (format == null)
            {
                return Long.valueOf(value);
            }
            else
            {
                return parseLong(value, format);
            }
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }


    /**
     * Extra method for better unit testing
     * 
     * @param value
     * @param format
     * @return
     */
    protected Long parseLong(String value, String format)
    {
        NumberFormat numberFormat = NumberFormat.getFormat(format);
        return new Long((long) numberFormat.parse(value));
    }
}
