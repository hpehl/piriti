package name.pehl.piriti.commons.client;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Abstract reader used as base class for JSON and XML readers.
 * 
 * @param <T>
 *            The type
 * @param <C>
 *            the context type
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public abstract class AbstractReader<T, C> extends ReaderWriterBase<T> implements HasModelReadHandler<T>
{
    // --------------------------------------------------------- new... methods

    protected abstract T newModel();


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
