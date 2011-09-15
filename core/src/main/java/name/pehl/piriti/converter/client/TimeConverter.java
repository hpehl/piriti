package name.pehl.piriti.converter.client;

import java.sql.Time;
import java.util.Date;
import java.util.logging.Level;

/**
 * Converter for {@link Time}.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class TimeConverter extends AbstractDateConverter<Time>
{
    @Override
    protected Time convertWithoutFormat(String value)
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


    @Override
    protected Time newInstance(Date parsed)
    {
        return new Time(parsed.getTime());
    }
}
