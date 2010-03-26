package name.pehl.piriti.sample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * @author $Author$
 * @version $Date$ $Revision$
 */
public class PiritiSample implements EntryPoint
{
    public void onModuleLoad()
    {
        RootLayoutPanel.get().add(new Application());
    }
}
