package name.pehl.gwt.piriti.client.xml;

import name.pehl.gwt.piriti.client.converter.ConverterRegistry;
import name.pehl.gwt.piriti.client.converter.ConverterRegistryImpl;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * Gin module which binds the default implementations for
 * {@link ConverterRegistry} and {@link XmlRegistry}.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class XmlReaderModule extends AbstractGinModule
{
    /**
     * Binds {@link ConverterRegistry} to {@link ConverterRegistryImpl} and
     * {@link XmlRegistry} to {@link ConverterRegistryImpl} both in
     * {@link Singleton} scope.
     * 
     * @see com.google.gwt.inject.client.AbstractGinModule#configure()
     */
    @Override
    protected void configure()
    {
        bind(ConverterRegistry.class).to(ConverterRegistryImpl.class).in(Singleton.class);
        bind(XmlRegistry.class).to(XmlRegistryImpl.class).in(Singleton.class);
    }
}
