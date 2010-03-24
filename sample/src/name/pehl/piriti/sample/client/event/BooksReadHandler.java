package name.pehl.piriti.sample.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public interface BooksReadHandler extends EventHandler
{
    void onTimeInterval(BooksReadEvent event);
}
