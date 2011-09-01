package name.pehl.piriti.commons.client;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Represents the event when a model is written as JSON / XML.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 * @param <T>
 *            the model type
 */
public class WriteModelEvent<T> extends GwtEvent<WriteModelHandler<T>>
{
    /**
     * Handler type.
     */
    private static Type<WriteModelHandler<?>> TYPE;


    /**
     * Fires a {@link WriteModelEvent} on all registered handlers. If no such
     * handlers exist, this method will do nothing.
     * 
     * @param <T>
     *            the model type
     * @param source
     *            the source of the handlers
     * @param model
     *            the model
     */
    public static <T> void fire(HasWriteModelHandler<T> source, T model)
    {
        if (TYPE != null)
        {
            WriteModelEvent<T> event = new WriteModelEvent<T>(model);
            source.fireEvent(event);
        }
    }


    /**
     * Gets the type associated with this event.
     * 
     * @return returns the handler type
     */
    public static Type<WriteModelHandler<?>> getType()
    {
        if (TYPE == null)
        {
            TYPE = new Type<WriteModelHandler<?>>();
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
    protected WriteModelEvent(T model)
    {
        this.model = model;
    }


    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public final Type<WriteModelHandler<T>> getAssociatedType()
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
    protected void dispatch(WriteModelHandler<T> handler)
    {
        handler.onWriteModel(this);
    }
}
