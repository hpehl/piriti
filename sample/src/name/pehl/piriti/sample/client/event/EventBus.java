package name.pehl.piriti.sample.client.event;

import com.google.gwt.event.shared.HandlerManager;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-05-18 23:30:44 +0200 (Di, 18 Mai 2010) $ $Revision: 327
 *          $
 */
public class EventBus
{
    private static EventBus instance = null;
    private HandlerManager handlerManager;


    private EventBus()
    {
        handlerManager = new HandlerManager(null);
    }


    public static HandlerManager get()
    {
        if (instance == null)
        {
            instance = new EventBus();
        }
        return instance.handlerManager;
    }
}
