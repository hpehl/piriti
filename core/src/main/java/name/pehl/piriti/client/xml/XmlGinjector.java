package name.pehl.piriti.client.xml;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

/**
 * Injector used to provide the implementation for {@link XmlRegistry}.
 * 
 * @see XmlModule
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 137 $
 */
@GinModules(XmlModule.class)
public interface XmlGinjector extends Ginjector
{
    /**
     * Injector instance
     */
    XmlGinjector INJECTOR = GWT.create(XmlGinjector.class);


    /**
     * Provides the implementation for the {@link XmlRegistry}
     * 
     * @return
     */
    XmlRegistry getXmlRegistry();


    /**
     * Provides the implementation for the {@link XmlParser} implementation
     * 
     * @return
     */
    XmlParser getXmlParser();
}
