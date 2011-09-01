package name.pehl.piriti.commons.client;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler for {@link ModelReadEvent} events.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 * @param <T>
 *            the model type
 */
public interface ModelReadHandler<T> extends EventHandler
{
    /**
     * Called <em>after</em> the model instance was read from JSON / XML.
     * 
     * @param event
     *            the {@link ModelReadEvent} that was fired
     */
    void onModelRead(ModelReadEvent<T> event);
}
