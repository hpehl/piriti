package name.pehl.piriti.client.converter;

import com.google.gwt.inject.client.AbstractGinModule;

/**
 * Gin module to bind the default implementation for {@link ConverterRegistry}.
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
        bind(ConverterRegistry.class);
    }
}
