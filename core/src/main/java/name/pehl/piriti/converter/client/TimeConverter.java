package name.pehl.piriti.converter.client;

import java.sql.Time;

/**
 * Converter for {@link Time}.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class TimeConverter extends AbstractConverter<Time>
{
    /**
     * Converts the specified value to a time. Therefore
     * {@link Time#valueOf(String)} is used.
     * 
     * @param value
     *            The string to be converted. May be <code>null</code>.
     * @param format
     *            Ignored
     * @return {@code null} if the value is {@code null} or empty, otherwise the
     *         converted time
     * @see name.pehl.piriti.converter.client.Converter#convert(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Time convert(String value, String format)
    {
        Time timestamp = null;
        if (value != null && value.trim().length() != 0)
        {
            try
            {
                timestamp = Time.valueOf(value);
            }
            catch (IllegalArgumentException e)
            {
                // nop
            }
        }
        return timestamp;
    }


    /**
     * Serializes the specified time to a string using {@link Time#toString()}
     * 
     * @param value
     *            the time to serialize
     * @param format
     *            Ignored
     * @return The string representation of the time as specified by
     *         {@link Time#toString()} or {@code null} if the value is
     *         {@code null}.
     * @see name.pehl.piriti.converter.client.Converter#serialize(java.lang.Object,
     *      java.lang.String)
     */
    @Override
    public String serialize(Time value, String format)
    {
        if (value == null)
        {
            return null;
        }
        return value.toString();
    }
}
