package name.pehl.piriti.client.json;

import name.pehl.piriti.client.json.internal.NativeJsonParserImpl;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * Gin module to bind the default implementation for {@link JsonRegistry}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 137 $
 */
public class JsonModule extends AbstractGinModule
{
    /**
     * Binds {@link JsonRegistry} to {@link JsonRegistryImpl} in
     * {@link Singleton} scope.
     * 
     * @see com.google.gwt.inject.client.AbstractGinModule#configure()
     */
    @Override
    protected void configure()
    {
        bind(JsonRegistry.class).to(JsonRegistryImpl.class).in(Singleton.class);
        bind(JsonParser.class).to(NativeJsonParserImpl.class).in(Singleton.class);
    }
}
