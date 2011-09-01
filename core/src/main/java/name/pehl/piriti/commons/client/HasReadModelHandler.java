package name.pehl.piriti.commons.client;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * A class that implements this interface provides registration for
 * {@link ReadModelHandler} instances.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 * @param <T>
 *            The model type
 */
public interface HasReadModelHandler<T> extends HasHandlers
{
    HandlerRegistration addReadModelHandler(ReadModelHandler<T> handler);
}
