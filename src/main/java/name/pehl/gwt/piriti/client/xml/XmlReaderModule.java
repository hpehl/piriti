package name.pehl.gwt.piriti.client.xml;

import name.pehl.gwt.piriti.client.converter.ConverterFactory;
import name.pehl.gwt.piriti.client.converter.ConverterFactoryImpl;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author $Author:$
 * @version $Revision:$
 */
public class XmlReaderModule extends AbstractGinModule
{
    @Override
    protected void configure()
    {
        bind(ConverterFactory.class).to(ConverterFactoryImpl.class).in(Singleton.class);
        bind(XmlRegistry.class).to(XmlRegistryImpl.class).in(Singleton.class);
    }
}
