package name.pehl.gwt.piriti.client.xml;

import name.pehl.gwt.piriti.client.converter.ConverterRegistry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

/**
 * Injector used to provide the implementations for {@link ConverterRegistry}
 * and {@link XmlRegistry}. See {@link XmlReaderModule} for further details.
 * 
 * @see XmlReaderModule
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
@GinModules(XmlReaderModule.class)
public interface XmlReaderGinjector extends Ginjector
{
    /**
     * Injector instance
     */
    XmlReaderGinjector INJECTOR = GWT.create(XmlReaderGinjector.class);


    /**
     * Provides the implementation for the {@link ConverterRegistry}
     * 
     * @return
     */
    ConverterRegistry getConverterRegistry();


    /**
     * Provides the implementation for the {@link XmlRegistry}
     * 
     * @return
     */
    XmlRegistry getXmlRegistry();
}
