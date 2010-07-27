package name.pehl.piriti.client.json;

import java.util.List;

import com.google.inject.internal.Nullable;

/**
 * Interface for serializing an instance of T or a list of Ts to JSON data. The
 * implementation for this interface is generated using deferred binding.
 * 
 * @param <T>
 *            The type
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 46 $
 */
public interface JsonWriter<T>
{
    String toJson(@Nullable List<T> values, String arrayKey);


    String toJson(@Nullable T value);
}
