package name.pehl.piriti.rebind;

import javax.inject.Inject;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.TreeLogger.Type;
import com.google.gwt.core.ext.UnableToCompleteException;

public final class Logger
{
    private final TreeLogger treeLogger;


    @Inject
    public Logger(TreeLogger treeLogger)
    {
        this.treeLogger = treeLogger;
    }


    public void debug(String message, Object... params)
    {
        internalLog(TreeLogger.DEBUG, String.format(message, params));

    }


    public void info(String message, Object... params)
    {
        internalLog(TreeLogger.INFO, String.format(message, params));
    }


    public void warn(String message, Object... params)
    {
        internalLog(TreeLogger.WARN, String.format(message, params));
    }


    /**
     * Post an error message and halt processing. This method always throws an
     * {@link UnableToCompleteException}
     */
    public void die(String message) throws UnableToCompleteException
    {
        internalLog(TreeLogger.ERROR, message);
        throw new UnableToCompleteException();
    }


    /**
     * Post an error message and halt processing. This method always throws an
     * {@link UnableToCompleteException}
     */
    public void die(String message, Object... params) throws UnableToCompleteException
    {
        internalLog(TreeLogger.ERROR, String.format(message, params));
        throw new UnableToCompleteException();
    }


    private void internalLog(Type type, String message)
    {
        if (treeLogger != null)
        {
            treeLogger.log(type, message);
        }
        else
        {
            System.out.println(message);
        }
    }
}
