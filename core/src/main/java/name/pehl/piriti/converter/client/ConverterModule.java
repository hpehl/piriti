package name.pehl.piriti.converter.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * Gin module to bind the {@link ConverterRegistry}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 8 $
 */
public class ConverterModule extends AbstractGinModule
{
    /**
     * Binds the {@link ConverterRegistry}.
     * 
     * @see com.google.gwt.inject.client.AbstractGinModule#configure()
     */
    @Override
    protected void configure()
    {
        bind(ConverterRegistry.class).in(Singleton.class);
    }
}
