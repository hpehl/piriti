package name.pehl.piriti.converter.client;

import java.sql.Date;
import java.util.logging.Level;

/**
 * Converter for {@link java.sqlDate}.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class SqlDateConverter extends AbstractConverter<Date>
{
    @Override
    public Date convert(String value)
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
}
