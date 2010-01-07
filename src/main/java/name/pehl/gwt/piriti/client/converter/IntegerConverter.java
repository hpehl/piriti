package name.pehl.gwt.piriti.client.converter;

import com.google.gwt.i18n.client.NumberFormat;

/**
 * @author $Author:$
 * @version $Revision:$
 */
public class IntegerConverter implements Converter<Integer>
{
    @Override
    public Integer convert(String value, String format)
    {
        if (value == null || value.trim().length() == 0)
        {
            return null;
        }
        try
        {
            if (format == null)
            {
                return Integer.valueOf(value);
            }
            else
            {
                return parseInteger(value, format);
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
    protected Integer parseInteger(String value, String format)
    {
        NumberFormat numberFormat = NumberFormat.getFormat(format);
        return new Integer((int) numberFormat.parse(value));
    }
}
