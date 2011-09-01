package name.pehl.piriti.commons.client;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Represents the event when a model is read from JSON / XML.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 * @param <T>
 *            the model type
 */
public class ReadModelEvent<T> extends GwtEvent<ReadModelHandler<T>>
{
    /**
     * Handler type.
     */
    private static Type<ReadModelHandler<?>> TYPE;


    /**
     * Fires a {@link ReadModelEvent} on all registered handlers. If no such
     * handlers exist, this method will do nothing.
     * 
     * @param <T>
     *            the model type
     * @param source
     *            the source of the handlers
     * @param model
     *            the model
     */
    public static <T> void fire(HasReadModelHandler<T> source, T model)
    {
        if (TYPE != null)
        {
            ReadModelEvent<T> event = new ReadModelEvent<T>(model);
            source.fireEvent(event);
        }
    }


    /**
     * Gets the type associated with this event.
     * 
     * @return returns the handler type
     */
    public static Type<ReadModelHandler<?>> getType()
    {
        if (TYPE == null)
        {
            TYPE = new Type<ReadModelHandler<?>>();
        }
        return TYPE;
    }

    private final T model;


    /**
     * Creates a new event.
     * 
     * @param model
     *            the model
     */
    protected ReadModelEvent(T model)
    {
        this.model = model;
    }


    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public final Type<ReadModelHandler<T>> getAssociatedType()
    {
        return (Type) TYPE;
    }


    /**
     * Gets the model.
     * 
     * @return the model
     */
    public T getModel()
    {
        return model;
    }


    @Override
    public String toDebugString()
    {
        return super.toDebugString() + getModel();
    }


    @Override
    protected void dispatch(ReadModelHandler<T> handler)
    {
        handler.onReadModel(this);
    }
}
