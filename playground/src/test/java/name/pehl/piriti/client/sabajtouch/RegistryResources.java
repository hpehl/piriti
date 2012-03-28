package name.pehl.piriti.client.sabajtouch;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 1305 $
 */

public interface RegistryResources extends ClientBundle
{
    RegistryResources INSTANCE = GWT.create(RegistryResources.class);


    @Source("registry.xml")
    public TextResource registryXml();
}
