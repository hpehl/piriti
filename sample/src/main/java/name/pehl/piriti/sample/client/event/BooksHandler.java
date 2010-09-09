package name.pehl.piriti.sample.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author $Author$
 * @version $Date$ $Revision: 327
 *          $
 */
public interface BooksHandler extends EventHandler
{
    void onBooks(BooksEvent event);
}
