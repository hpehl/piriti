package name.pehl.piriti.commons.client;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * A class that implements this interface provides registration for
 * {@link WriteModelHandler} instances.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 * @param <T>
 *            The model type
 */
public interface HasWriteModelHandler<T> extends HasHandlers
{
    HandlerRegistration addWriteModelHandler(WriteModelHandler<T> handler);
}
