package name.pehl.piriti.converter.client;

import java.sql.Timestamp;
import java.util.logging.Level;

/**
 * Converter for {@link Timestamp}.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class TimestampConverter extends AbstractConverter<Timestamp>
{
    @Override
    public Timestamp convert(String value)
    {
        Timestamp timestamp = null;
        try
        {
            timestamp = Timestamp.valueOf(value);
        }
        catch (IllegalArgumentException e)
        {
            logger.log(Level.SEVERE, "Cannot parse SQL timestamp '" + value + "': " + e.getMessage(), e);
        }
        return timestamp;
    }
}
