package name.pehl.piriti.commons.client;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler for {@link ModelReadEvent} events.
 * 
 * @param <T>
 *            the model type
 * @param <C>
 *            the context type
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public interface ModelReadHandler<T, C> extends EventHandler
{
    /**
     * Called <em>after</em> the model instance was read from JSON / XML.
     * 
     * @param event
     *            the {@link ModelReadEvent} that was fired
     */
    void onModelRead(ModelReadEvent<T, C> event);
}
