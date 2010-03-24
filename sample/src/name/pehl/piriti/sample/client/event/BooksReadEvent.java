package name.pehl.piriti.sample.client.event;

import java.util.List;

import name.pehl.piriti.sample.client.util.TimeInterval;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class BooksReadEvent extends GwtEvent<BooksReadHandler>
{
    private static Type<BooksReadHandler> TYPE;


    public static Type<BooksReadHandler> getType()
    {
        if (TYPE == null)
        {
            TYPE = new Type<BooksReadHandler>();
        }
        return TYPE;
    }

    private final List<?> books;
    private final TimeInterval timeInterval;


    public BooksReadEvent(List<?> books, TimeInterval timeInterval)
    {
        super();
        this.books = books;
        this.timeInterval = timeInterval;
    }


    @Override
    protected void dispatch(BooksReadHandler handler)
    {
        handler.onTimeInterval(this);
    }


    @Override
    public Type<BooksReadHandler> getAssociatedType()
    {
        return getType();
    }


    @SuppressWarnings("unchecked")
    public <T> List<T> getBooks()
    {
        return (List<T>) books;
    }


    public TimeInterval getTimeInterval()
    {
        return timeInterval;
    }
}
