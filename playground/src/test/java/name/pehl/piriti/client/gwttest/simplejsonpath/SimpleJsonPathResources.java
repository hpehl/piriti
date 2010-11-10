package name.pehl.piriti.client.gwttest.simplejsonpath;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 1305 $
 */

public interface SimpleJsonPathResources extends ClientBundle
{
    SimpleJsonPathResources INSTANCE = GWT.create(SimpleJsonPathResources.class);


    @Source("person.json")
    public TextResource personJson();
}
