package name.pehl.piriti.client.converter;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * Gin module to bind the default implementation for {@link ConverterRegistry}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 8 $
 */
public class ConverterModule extends AbstractGinModule
{
    /**
     * Binds {@link ConverterRegistry} to {@link ConverterRegistryImpl} in
     * {@link Singleton} scope.
     * 
     * @see com.google.gwt.inject.client.AbstractGinModule#configure()
     */
    @Override
    protected void configure()
    {
        bind(ConverterRegistry.class).to(ConverterRegistryImpl.class).in(Singleton.class);
    }
}
