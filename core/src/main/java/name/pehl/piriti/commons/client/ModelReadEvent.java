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
public class ModelReadEvent<T> extends GwtEvent<ModelReadHandler<T>>
{
    /**
     * Handler type.
     */
    private static Type<ModelReadHandler<?>> TYPE;


    /**
     * Fires a {@link ModelReadEvent} on all registered handlers. If no such
     * handlers exist, this method will do nothing.
     * 
     * @param <T>
     *            the model type
     * @param source
     *            the source of the handlers
     * @param model
     *            the model
     */
    public static <T> void fire(HasModelReadHandler<T> source, T model)
    {
        if (TYPE != null)
        {
            ModelReadEvent<T> event = new ModelReadEvent<T>(model);
            source.fireEvent(event);
        }
    }


    /**
     * Gets the type associated with this event.
     * 
     * @return returns the handler type
     */
    public static Type<ModelReadHandler<?>> getType()
    {
        if (TYPE == null)
        {
            TYPE = new Type<ModelReadHandler<?>>();
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
    protected ModelReadEvent(T model)
    {
        this.model = model;
    }


    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public final Type<ModelReadHandler<T>> getAssociatedType()
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
    protected void dispatch(ModelReadHandler<T> handler)
    {
        handler.onModelRead(this);
    }
}
