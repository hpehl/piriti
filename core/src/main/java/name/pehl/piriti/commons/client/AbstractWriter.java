package name.pehl.piriti.commons.client;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Abstract reader used as base class for JSON and XML readers.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public abstract class AbstractWriter<T> extends ReaderWriterBase<T> implements HasModelWriteHandler<T>
{
    // --------------------------------------------------------- event handlers

    @Override
    public HandlerRegistration addModelWriteHandler(ModelWriteHandler<T> handler)
    {
        return handlerManager.addHandler(ModelWriteEvent.getType(), handler);
    }


    @Override
    public void fireEvent(GwtEvent<?> event)
    {
        handlerManager.fireEvent(event);
    }
}
