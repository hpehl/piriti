package name.pehl.piriti.sample.client.event;

import com.google.gwt.event.shared.SimpleEventBus;

/**
 * @author $Author$
 * @version $Date$ $Revision: 327
 *          $
 */
public class EventBus
{
    private static EventBus instance = null;
    private final SimpleEventBus eventBus;


    private EventBus()
    {
        eventBus = new SimpleEventBus();
    }


    public static SimpleEventBus get()
    {
        if (instance == null)
        {
            instance = new EventBus();
        }
        return instance.eventBus;
    }
}
