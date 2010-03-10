package name.pehl.gwt.piriti.client.json;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

/**
 * Injector used to provide the implementation for {@link JsonRegistry}.
 * 
 * @see JsonModule
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 137 $
 */
@GinModules(JsonModule.class)
public interface JsonGinjector extends Ginjector
{
    /**
     * Injector instance
     */
    JsonGinjector INJECTOR = GWT.create(JsonGinjector.class);


    /**
     * Provides the implementation for the {@link JsonRegistry}
     * 
     * @return
     */
    JsonRegistry getJsonRegistry();
}
