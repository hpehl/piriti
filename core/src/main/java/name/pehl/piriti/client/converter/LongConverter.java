package name.pehl.piriti.client.converter;

import com.google.gwt.i18n.client.NumberFormat;

/**
 * Converter for long objects. Uses {@code Long.valueOf(value)} if no format is
 * specified and {@link NumberFormat#parse(String)} otherwise.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 7 $
 */
public class LongConverter extends AbstractConverter<Long>
{
    /**
     * Converts the specified value to long.
     * 
     * @param value
     *            The string to be converted
     * @param format
     *            Must be a valid number format or {@code null}
     * @return {@code null} if the value is {@code null}, empty or in the wrong
     *         format, otherwise the converted long
     * @see name.pehl.piriti.client.converter.Converter#convert(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Long convert(String value, String format)
    {
        if (isValid(value))
        {
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
    protected Long parseLong(String value, String format)
    {
        NumberFormat numberFormat = NumberFormat.getFormat(format);
        return new Long((long) numberFormat.parse(value));
    }
}
