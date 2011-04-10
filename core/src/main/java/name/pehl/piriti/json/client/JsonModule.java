package name.pehl.piriti.json.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * Gin module to bind the {@link JsonRegistry}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 137 $
 */
public class JsonModule extends AbstractGinModule
{
    /**
     * Binds the {@link JsonRegistry}.
     * 
     * @see com.google.gwt.inject.client.AbstractGinModule#configure()
     */
    @Override
    protected void configure()
    {
        bind(JsonRegistry.class).in(Singleton.class);
    }
}
