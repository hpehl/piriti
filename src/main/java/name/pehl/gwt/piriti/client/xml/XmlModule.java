package name.pehl.gwt.piriti.client.xml;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * Gin module to bind the default implementation for {@link XmlRegistry}.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class XmlModule extends AbstractGinModule
{
    /**
     * Binds {@link XmlRegistry} to {@link XmlRegistryImpl} in {@link Singleton}
     * scope.
     * 
     * @see com.google.gwt.inject.client.AbstractGinModule#configure()
     */
    @Override
    protected void configure()
    {
        bind(XmlRegistry.class).to(XmlRegistryImpl.class).in(Singleton.class);
    }
}
