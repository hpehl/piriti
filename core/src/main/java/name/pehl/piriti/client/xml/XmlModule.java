package name.pehl.piriti.client.xml;

import name.pehl.piriti.client.xml.internal.GwtXPathImpl;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * Gin module to bind the default implementation for {@link XmlRegistry}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 138 $
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
        bind(XPath.class).to(GwtXPathImpl.class).in(Singleton.class);
    }
}
