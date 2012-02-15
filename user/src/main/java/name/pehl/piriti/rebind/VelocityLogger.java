package name.pehl.piriti.rebind;

import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.log.LogChute;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.TreeLogger.Type;

public class VelocityLogger implements LogChute
{
    private final TreeLogger logger;


    public VelocityLogger()
    {
        this.logger = Logger.get().getTreeLogger();
    }


    @Override
    public void init(RuntimeServices rs) throws Exception
    {
        // nothing to initialize
    }


    @Override
    public boolean isLevelEnabled(int level)
    {
        boolean enabled = false;
        switch (level)
        {
            case TRACE_ID:
                enabled = logger.isLoggable(Type.TRACE);
                break;

            case DEBUG_ID:
                enabled = logger.isLoggable(Type.DEBUG);
                break;

            case INFO_ID:
                enabled = logger.isLoggable(Type.INFO);
                break;

            case WARN_ID:
                enabled = logger.isLoggable(Type.WARN);
                break;

            case ERROR_ID:
                enabled = logger.isLoggable(Type.ERROR);
                break;

            default:
                break;
        }
        return enabled;
    }


    @Override
    public void log(int level, String message)
    {
        internalLog(level, message);
    }


    @Override
    public void log(int level, String message, Throwable t)
    {
        internalLog(level, message, t);
    }


    private void internalLog(int level, String message, Throwable... t)
    {
        Type type = typeFromLevel(level);
        if (t != null && t.length > 0)
        {
            logger.log(type, message, t[0]);
        }
        else
        {
            logger.log(type, message);
        }
    }


    private Type typeFromLevel(int level)
    {
        Type type = Type.INFO;
        switch (level)
        {
            case TRACE_ID:
                type = Type.TRACE;
                break;

            case DEBUG_ID:
                type = Type.DEBUG;
                break;

            case INFO_ID:
                type = Type.INFO;
                break;

            case WARN_ID:
                type = Type.WARN;
                break;

            case ERROR_ID:
                type = Type.ERROR;
                break;

            default:
                break;
        }
        return type;
    }
}
