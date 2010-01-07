package name.pehl.gwt.piriti.client.converter;

import com.google.gwt.i18n.client.NumberFormat;

/**
 * @author $Author:$
 * @version $Revision:$
 */
public class DoubleConverter implements Converter<Double>
{
    @Override
    public Double convert(String value, String format)
    {
        if (value == null || value.trim().length() == 0)
        {
            return null;
        }
        try
        {
            if (format == null)
            {
                return Double.valueOf(value);
            }
            else
            {
                return parseDouble(value, format);
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
    protected Double parseDouble(String value, String format)
    {
        NumberFormat numberFormat = NumberFormat.getFormat(format);
        return numberFormat.parse(value);
    }
}
