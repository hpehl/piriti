package name.pehl.piriti.sample.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-05-18 23:30:44 +0200 (Di, 18 Mai 2010) $ $Revision: 327
 *          $
 */
public interface BooksHandler extends EventHandler
{
    void onBooks(BooksEvent event);
}
