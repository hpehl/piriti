package name.pehl.piriti.client.xml;

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
     * Binds the {@link XmlRegistry}.
     * 
     * @see com.google.gwt.inject.client.AbstractGinModule#configure()
     */
    @Override
    protected void configure()
    {
        bind(XmlRegistry.class).in(Singleton.class);
    }
}
