package name.pehl.gwt.piriti.client.xml;

import name.pehl.gwt.piriti.client.converter.ConverterRegistry;
import name.pehl.gwt.piriti.client.converter.ConverterRegistryImpl;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class XmlReaderModule extends AbstractGinModule
{
    @Override
    protected void configure()
    {
        bind(ConverterRegistry.class).to(ConverterRegistryImpl.class).in(Singleton.class);
        bind(XmlRegistry.class).to(XmlRegistryImpl.class).in(Singleton.class);
    }
}
