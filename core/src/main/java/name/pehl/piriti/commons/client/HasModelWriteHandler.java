package name.pehl.piriti.commons.client;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * A class that implements this interface provides registration for
 * {@link ModelWriteHandler} instances.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 * @param <T>
 *            The model type
 */
public interface HasModelWriteHandler<T> extends HasHandlers
{
    HandlerRegistration addModelWriteHandler(ModelWriteHandler<T> handler);
}
