package name.pehl.piriti.rebind;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class LogFacade
{
    protected final TreeLogger logger;


    protected LogFacade(TreeLogger logger)
    {
        super();
        this.logger = logger;
    }


    protected void debug(String message, Object... params)
    {
        logger.log(TreeLogger.DEBUG, String.format(message, params));
    }


    protected void info(String message, Object... params)
    {
        logger.log(TreeLogger.INFO, String.format(message, params));
    }


    protected void warn(String message, Object... params)
    {
        logger.log(TreeLogger.WARN, String.format(message, params));
    }


    /**
     * Post an error message and halt processing. This method always throws an
     * {@link UnableToCompleteException}
     */
    protected void die(String message) throws UnableToCompleteException
    {
        logger.log(TreeLogger.ERROR, message);
        throw new UnableToCompleteException();
    }


    /**
     * Post an error message and halt processing. This method always throws an
     * {@link UnableToCompleteException}
     */
    protected void die(String message, Object... params) throws UnableToCompleteException
    {
        logger.log(TreeLogger.ERROR, String.format(message, params));
        throw new UnableToCompleteException();
    }
}
