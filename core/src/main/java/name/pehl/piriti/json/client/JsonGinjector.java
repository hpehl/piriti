package name.pehl.piriti.json.client;


import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
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
