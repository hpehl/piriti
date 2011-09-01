package name.pehl.piriti.commons.client;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * A class that implements this interface provides registration for
 * {@link ModelReadHandler} instances.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 * @param <T>
 *            The model type
 */
public interface HasModelReadHandler<T> extends HasHandlers
{
    HandlerRegistration addModelReadHandler(ModelReadHandler<T> handler);
}
