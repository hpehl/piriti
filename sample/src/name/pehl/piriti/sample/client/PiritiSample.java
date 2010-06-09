package name.pehl.piriti.sample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-05-18 23:30:44 +0200 (Di, 18 Mai 2010) $ $Revision: 327
 *          $
 */
public class PiritiSample implements EntryPoint
{
    public void onModuleLoad()
    {
        RootLayoutPanel.get().add(new Application());
    }
}
