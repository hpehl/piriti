package name.pehl.piriti.commons.client;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Abstract reader used as base class for JSON and XML readers.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public abstract class AbstractReader<T> extends ReaderWriterBase<T> implements HasModelReadHandler<T>
{
    // --------------------------------------------------------- event handlers

    @Override
    public HandlerRegistration addModelReadHandler(ModelReadHandler<T> handler)
    {
        return handlerManager.addHandler(ModelReadEvent.getType(), handler);
    }


    @Override
    public void fireEvent(GwtEvent<?> event)
    {
        handlerManager.fireEvent(event);
    }
}
