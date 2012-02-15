package name.pehl.piriti.converter.client;

import java.sql.Time;
import java.util.logging.Level;

/**
 * Converter for {@link Time}.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class TimeConverter extends AbstractConverter<Time>
{
    @Override
    public Time convert(String value)
    {
        Time time = null;
        try
        {
            time = Time.valueOf(value);
        }
        catch (IllegalArgumentException e)
        {
            logger.log(Level.SEVERE, "Cannot parse SQL time '" + value + "': " + e.getMessage(), e);
        }
        return time;
    }
}
