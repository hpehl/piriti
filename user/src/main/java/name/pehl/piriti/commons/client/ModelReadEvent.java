package name.pehl.piriti.commons.client;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Represents the event when a model is read from JSON / XML.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 * @param <T>
 *            the model type
 * @param <C>
 *            the context type
 */
public class ModelReadEvent<T, C> extends GwtEvent<ModelReadHandler<T, C>>
{
    /**
     * Handler type.
     */
    private static Type<ModelReadHandler<?, ?>> TYPE;
    private final T model;
    private final C context;


    /**
     * Creates a new event.
     *
     * @param model
     *            the model
     * @param context
     *            the context i.e the JSONObject or XML element
     */
    protected ModelReadEvent(T model, C context)
    {
        this.model = model;
        this.context = context;
    }


    /**
     * Fires a {@link ModelReadEvent} on all registered handlers. If no such
     * handlers exist, this method will do nothing.
     * 
     * @param source
     *            the source of the handlers
     * @param model
     *            the model
     * @param <T>
     *            the model type
     * @param <C>
     *            the context type
     */
    public static <T, C> void fire(HasModelReadHandler<T, C> source, T model, C context)
    {
        if (TYPE != null)
        {
            ModelReadEvent<T, C> event = new ModelReadEvent<T, C>(model, context);
            source.fireEvent(event);
        }
    }


    /**
     * Gets the type associated with this event.
     * 
     * @return returns the handler type
     */
    public static Type<ModelReadHandler<?, ?>> getType()
    {
        if (TYPE == null)
        {
            TYPE = new Type<ModelReadHandler<?, ?>>();
        }
        return TYPE;
    }


    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public final Type<ModelReadHandler<T, C>> getAssociatedType()
    {
        return (Type) TYPE;
    }


    /**
     * Returns the model.
     * 
     * @return the model
     */
    public T getModel()
    {
        return model;
    }


    /**
     * Returns the context i.e the JSONObject or XML element.
     * 
     * @return the context i.e the JSONObject or XML element
     */
    public C getContext()
    {
        return context;
    }


    @Override
    public String toDebugString()
    {
        return super.toDebugString() + getModel() + getContext();
    }


    @Override
    protected void dispatch(ModelReadHandler<T, C> handler)
    {
        handler.onModelRead(this);
    }
}
