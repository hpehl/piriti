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
public class ModelWriteEvent<T> extends GwtEvent<ModelWriteHandler<T>>
{
    /**
     * Handler type.
     */
    private static Type<ModelWriteHandler<?>> TYPE;


    /**
     * Fires a {@link ModelWriteEvent} on all registered handlers. If no such
     * handlers exist, this method will do nothing.
     * 
     * @param source
     *            the source of the handlers
     * @param model
     *            the model
     * @param representation
     *            the string representation
     * @param <T>
     *            the model type
     */
    public static <T> void fire(HasModelWriteHandler<T> source, T model, String representation)
    {
        if (TYPE != null)
        {
            ModelWriteEvent<T> event = new ModelWriteEvent<T>(model, representation);
            source.fireEvent(event);
        }
    }


    /**
     * Gets the type associated with this event.
     * 
     * @return returns the handler type
     */
    public static Type<ModelWriteHandler<?>> getType()
    {
        if (TYPE == null)
        {
            TYPE = new Type<ModelWriteHandler<?>>();
        }
        return TYPE;
    }

    private final T model;
    private final String representation;


    /**
     * Creates a new event.
     * 
     * @param model
     *            the model
     * @param representation
     *            the string representation of the model
     */
    protected ModelWriteEvent(T model, String representation)
    {
        this.model = model;
        this.representation = representation;
    }


    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public final Type<ModelWriteHandler<T>> getAssociatedType()
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
     * Returns the string representation of the model.
     * 
     * @return the string representation
     */
    public String getRepresentation()
    {
        return representation;
    }


    @Override
    public String toDebugString()
    {
        return super.toDebugString() + getModel();
    }


    @Override
    protected void dispatch(ModelWriteHandler<T> handler)
    {
        handler.onModelWrite(this);
    }
}
