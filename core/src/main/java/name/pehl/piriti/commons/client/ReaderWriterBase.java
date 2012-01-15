package name.pehl.piriti.commons.client;

import java.util.Map;
import java.util.logging.Logger;

import com.google.gwt.event.shared.HandlerManager;

/**
 * Common code for all readers and writers.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public abstract class ReaderWriterBase<T>
{
    // ----------------------------------------------------------------- fields

    protected final Logger logger;
    protected final HandlerManager handlerManager;
    protected final Map<String, T> idMap;


    // ----------------------------------------------------------- constructors

    protected ReaderWriterBase()
    {
        super();
        this.logger = Logger.getLogger(getClass().getName());
        this.handlerManager = new HandlerManager(this);
        this.idMap = newIdMap();
    }


    protected abstract Map<String, T> newIdMap();
}
