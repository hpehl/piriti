package name.pehl.piriti.converter.client;

import java.sql.Date;
import java.util.logging.Level;

/**
 * Converter for {@link java.sqlDate}.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class SqlDateConverter extends AbstractDateConverter<Date>
{
    @Override
    protected Date convertWithoutFormat(String value)
    {
        Date sqlDate = null;
        try
        {
            sqlDate = Date.valueOf(value);
        }
        catch (IllegalArgumentException e)
        {
            logger.log(Level.SEVERE, "Cannot parse SQL date '" + value + "': " + e.getMessage(), e);
        }
        return sqlDate;
    }


    @Override
    protected Date newInstance(java.util.Date parsed)
    {
        return new Date(parsed.getTime());
    }
}
