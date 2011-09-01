package name.pehl.piriti.commons.client;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler for {@link WriteModelEvent} events.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 * @param <T>
 *            the model type
 */
public interface WriteModelHandler<T> extends EventHandler
{
    /**
     * Called <em>before</em> the model instance is written as JSON / XML.
     * 
     * @param event
     *            the {@link ReadModelEvent} that was fired
     */
    void onWriteModel(WriteModelEvent<T> event);
}
