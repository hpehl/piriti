package name.pehl.piriti.sample.client.event;

import java.util.List;

import name.pehl.piriti.sample.client.util.TimeInterval;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author $Author$
 * @version $Date$ $Revision: 280
 *          $
 */
public class BooksEvent extends GwtEvent<BooksHandler>
{
    private static Type<BooksHandler> TYPE;


    public static Type<BooksHandler> getType()
    {
        if (TYPE == null)
        {
            TYPE = new Type<BooksHandler>();
        }
        return TYPE;
    }

    private final List<?> books;
    private final TimeInterval timeInterval;
    private final String sourceCode;


    public BooksEvent(List<?> books, TimeInterval timeInterval, String sourceCode)
    {
        super();
        this.books = books;
        this.timeInterval = timeInterval;
        this.sourceCode = sourceCode;
    }


    @Override
    protected void dispatch(BooksHandler handler)
    {
        handler.onBooks(this);
    }


    @Override
    public Type<BooksHandler> getAssociatedType()
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


    public String getSourceCode()
    {
        return sourceCode;
    }
}
