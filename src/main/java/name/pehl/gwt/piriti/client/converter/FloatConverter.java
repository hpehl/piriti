package name.pehl.gwt.piriti.client.converter;

import com.google.gwt.i18n.client.NumberFormat;

/**
 * @author $Author:$
 * @version $Revision:$
 */
public class FloatConverter implements Converter<Float>
{
    @Override
    public Float convert(String value, String format)
    {
        if (value == null || value.trim().length() == 0)
        {
            return null;
        }
        try
        {
            if (format == null)
            {
                return Float.valueOf(value);
            }
            else
            {
                return parseFloat(value, format);
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
    protected Float parseFloat(String value, String format)
    {
        NumberFormat numberFormat = NumberFormat.getFormat(format);
        return new Float((float) numberFormat.parse(value));
    }
}
