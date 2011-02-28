package name.pehl.piriti.client.converter;

import java.sql.Timestamp;

/**
 * Converter for {@link Timestamp}.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class TimestampConverter implements Converter<Timestamp>
{
    /**
     * Converts the specified value to a timestamp. Therefore
     * {@link Timestamp#valueOf(String)} is used.
     * 
     * @param value
     *            The string to be converted. May be <code>null</code>.
     * @param format
     *            Ignored
     * @return {@code null} if the value is {@code null} or empty, otherwise the
     *         converted timestamp
     * @see name.pehl.piriti.client.converter.Converter#convert(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Timestamp convert(String value, String format)
    {
        Timestamp timestamp = null;
        if (value != null && value.trim().length() != 0)
        {
            try
            {
                timestamp = Timestamp.valueOf(value);
            }
            catch (IllegalArgumentException e)
            {
                // nop
            }
        }
        return timestamp;
    }


    /**
     * Serializes the specified timestamp to a string using
     * {@link Timestamp#toString()}
     * 
     * @param value
     *            the timestamp to serialize
     * @param format
     *            Ignored
     * @return The string representation of the timestamp as specified by
     *         {@link Timestamp#toString()} or {@code null} if the value is
     *         {@code null}.
     * @see name.pehl.piriti.client.converter.Converter#serialize(java.lang.Object,
     *      java.lang.String)
     */
    @Override
    public String serialize(Timestamp value, String format)
    {
        if (value == null)
        {
            return null;
        }
        return value.toString();
    }
}
