package name.pehl.piriti.commons.client;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler for {@link ReadModelEvent} events.
 * 
 * @author upudxv4
 * @param <T>
 *            the model type
 */
public interface ReadModelHandler<T> extends EventHandler
{
    /**
     * Called <em>after</em> the model instance was read from JSON / XML.
     * 
     * @param event
     *            the {@link ReadModelEvent} that was fired
     */
    void onReadModel(ReadModelEvent<T> event);
}
