package name.pehl.piriti.converter.client;

import java.sql.Date;

/**
 * Converter for {@link Date}.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class SqlDateConverter extends AbstractConverter<Date>
{
    /**
     * Converts the specified value to a sql date. Therefore
     * {@link Date#valueOf(String)} is used.
     * 
     * @param value
     *            The string to be converted. May be <code>null</code>.
     * @param format
     *            Ignored
     * @return {@code null} if the value is {@code null} or empty, otherwise the
     *         converted date
     * @see name.pehl.piriti.converter.client.Converter#convert(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Date convert(String value, String format)
    {
        Date timestamp = null;
        if (value != null && value.trim().length() != 0)
        {
            try
            {
                timestamp = Date.valueOf(value);
            }
            catch (IllegalArgumentException e)
            {
                // nop
            }
        }
        return timestamp;
    }


    /**
     * Serializes the specified date to a string using {@link Date#toString()}
     * 
     * @param value
     *            the sql date to serialize
     * @param format
     *            Ignored
     * @return The string representation of the date as specified by
     *         {@link Date#toString()} or {@code null} if the value is
     *         {@code null}.
     * @see name.pehl.piriti.converter.client.Converter#serialize(java.lang.Object,
     *      java.lang.String)
     */
    @Override
    public String serialize(Date value, String format)
    {
        if (value == null)
        {
            return null;
        }
        return value.toString();
    }
}
