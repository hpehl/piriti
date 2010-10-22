package name.pehl.piriti.client.gwttest.specialjsonkey;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */

public interface RequestResources extends ClientBundle
{
    RequestResources INSTANCE = GWT.create(RequestResources.class);


    @Source("request.json")
    public TextResource requestJson();
}
