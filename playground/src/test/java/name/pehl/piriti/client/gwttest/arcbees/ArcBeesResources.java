package name.pehl.piriti.client.gwttest.arcbees;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */

public interface ArcBeesResources extends ClientBundle
{
    ArcBeesResources INSTANCE = GWT.create(ArcBeesResources.class);


    @Source("feed.xml")
    public TextResource feedXml();
}
