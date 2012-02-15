package name.pehl.piriti.json.client;

import name.pehl.piriti.commons.client.InstanceCreator;

import com.google.gwt.json.client.JSONValue;

/**
 * Abstract base class for JSON instance creators which uses an
 * {@link JSONValue} as context.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public abstract class JsonInstanceCreator<T> implements InstanceCreator<T, JSONValue>
{
}
