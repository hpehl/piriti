package name.pehl.piriti.converter.client;

import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;

/**
 * Converter for {@link Timestamp}.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class TimestampConverter extends AbstractDateConverter<Timestamp>
{
    @Override
    protected Timestamp convertWithoutFormat(String value)
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


    @Override
    protected Timestamp newInstance(Date parsed)
    {
        return new Timestamp(parsed.getTime());
    }
}
