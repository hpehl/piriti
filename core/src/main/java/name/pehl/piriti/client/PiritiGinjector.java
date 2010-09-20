package name.pehl.piriti.client;

import name.pehl.piriti.client.converter.ConverterModule;
import name.pehl.piriti.client.converter.ConverterRegistry;
import name.pehl.piriti.client.json.JsonModule;
import name.pehl.piriti.client.json.JsonParser;
import name.pehl.piriti.client.json.JsonRegistry;
import name.pehl.piriti.client.xml.XmlModule;
import name.pehl.piriti.client.xml.XmlRegistry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
@GinModules({ConverterModule.class, JsonModule.class, XmlModule.class})
public interface PiritiGinjector
{
    /**
     * Injector instance
     */
    PiritiGinjector INJECTOR = GWT.create(PiritiGinjector.class);


    /**
     * Provides the implementation for the {@link ConverterRegistry}
     * 
     * @return
     */
    ConverterRegistry getConverterRegistry();


    /**
     * Provides the implementation for the {@link JsonRegistry}
     * 
     * @return
     */
    JsonRegistry getJsonRegistry();


    /**
     * Provides the implementation for the {@link JsonParser} implementation
     * 
     * @return
     */
    JsonParser getJsonParser();


    /**
     * Provides the implementation for the {@link XmlRegistry}
     * 
     * @return
     */
    XmlRegistry getXmlRegistry();
}
