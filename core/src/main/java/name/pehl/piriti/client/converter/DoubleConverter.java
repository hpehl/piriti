package name.pehl.piriti.client.converter;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.inject.internal.Nullable;

/**
 * Converter for double objects. Uses {@code Double.valueOf(value)} if no format
 * is specified and {@link NumberFormat#parse(String)} otherwise.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 7 $
 */
public class DoubleConverter extends AbstractConverter<Double>
{
    /**
     * Converts the specified value to double.
     * 
     * @param value
     *            The string to be converted. May be <code>null</code>.
     * @param format
     *            Must be a valid number format or {@code null}
     * @return {@code null} if the value is {@code null}, empty or in the wrong
     *         format, otherwise the converted double
     * @see name.pehl.piriti.client.converter.Converter#convert(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Double convert(@Nullable String value, @Nullable String format)
    {
        if (isValid(value))
        {
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
        return null;
    }


    /**
     * Parsing happens in an extra method so it can be overwritten in unit
     * tests.
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


    @Override
    public String serialize(Double value, String format)
    {
        if (value != null && format != null)
        {
            return serializeDouble(value, format);
        }
        return super.serialize(value, format);
    }


    /**
     * Serialization happens in an extra method so it can be overwritten in unit
     * tests.
     * 
     * @param value
     * @param format
     */
    protected String serializeDouble(Double value, String format)
    {
        NumberFormat numberFormat = NumberFormat.getFormat(format);
        return numberFormat.format(value);
    }
}
