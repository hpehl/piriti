package name.pehl.piriti.sample.client.event;

import com.google.gwt.event.shared.HandlerManager;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
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
