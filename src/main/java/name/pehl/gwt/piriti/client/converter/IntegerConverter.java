package name.pehl.gwt.piriti.client.converter;

import com.google.gwt.i18n.client.NumberFormat;

/**
 * Converter for integer objects. Uses {@code Integer.valueOf(value)} if no
 * format is specified and {@link NumberFormat#parse(String)} otherwise.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class IntegerConverter extends AbstractConverter<Integer>
{
    /**
     * Converts the specified value to integer.
     * 
     * @param value
     *            The string to be converted
     * @param format
     *            Must be a valid number format or {@code null}
     * @return {@code null} if the value is {@code null}, empty or in the wrong
     *         format, otherwise the converted integer
     * @see name.pehl.gwt.piriti.client.converter.Converter#convert(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Integer convert(String value, String format)
    {
        if (isValid(value))
        {
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
    protected Integer parseInteger(String value, String format)
    {
        NumberFormat numberFormat = NumberFormat.getFormat(format);
        return new Integer((int) numberFormat.parse(value));
    }
}
