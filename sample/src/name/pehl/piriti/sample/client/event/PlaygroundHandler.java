package name.pehl.piriti.sample.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-03-26 10:41:28 +0100 (Fr, 26. Mär 2010) $ $Revision: 327
 *          $
 */
public interface PlaygroundHandler extends EventHandler
{
    void onPlayground(PlaygroundEvent event);
}
