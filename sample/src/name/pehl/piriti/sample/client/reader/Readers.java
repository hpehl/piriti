package name.pehl.piriti.sample.client.reader;

import name.pehl.piriti.client.book.Book.BookJsonReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public interface Readers
{
    public interface Book
    {
        BookXmlReader XML = GWT.create(BookXmlReader.class);
        BookJsonReader JSON = GWT.create(BookJsonReader.class);
    }
}
